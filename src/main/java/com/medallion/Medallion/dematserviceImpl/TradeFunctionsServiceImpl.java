package com.medallion.Medallion.dematserviceImpl;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.medallion.Medallion.dematservice.TradeFunctionService;
import com.medallion.Medallion.dto.DirectionalProbabilityDto;
import com.medallion.Medallion.dto.SimulationPathDto;
import com.medallion.Medallion.dto.SimulationResult;
import com.medallion.Medallion.dto.SingleDay;
import com.medallion.Medallion.dto.VolumeData;
import com.medallion.Medallion.validation.ValidateMedallion;

@Service
public class TradeFunctionsServiceImpl implements TradeFunctionService {

	private static final int TRADING_DAYS_PER_YEAR = 252;
	private static final double DELTA = 1.0 / TRADING_DAYS_PER_YEAR;

	@Autowired
	private MedallionAlgorithms medallionAlgorithms;

	@Autowired
	private ValidateMedallion validateMedallion;

	@Autowired
	@Qualifier("simulationPool")
	private ForkJoinPool simulationPool; // injected dedicated pool

	// ── Run simulations ──────────────────────────────────────────────────
	private static final int PERCENT_MULTIPLIER = 100;

	@Override
	public DirectionalProbabilityDto calculateDirectionalProbability(List<Double> prices, VolumeData volumeData,
			double currentPrice, int simulations) {
		medallionAlgorithms.validatePrices(prices);
		medallionAlgorithms.validateVolumeData(volumeData);
		double annualVolatility = medallionAlgorithms.calculateVolatility(prices);
		VolumeData computedVolume = medallionAlgorithms.calculateVolume(volumeData);
		double volumeFactor = Math.min(computedVolume.getCurrVolume() / computedVolume.getAvgVolume(), 2.0);
		double adjustedVolatility = annualVolatility * volumeFactor;
		double sumLogReturns = 0;
		for (int i = 1; i < prices.size(); i++) {
			sumLogReturns += Math.log(prices.get(i) / prices.get(i - 1));
		}
		double avgDailyReturn = sumLogReturns / (prices.size() - 1);
		double annualizedReturn = avgDailyReturn * TRADING_DAYS_PER_YEAR;
		validateMedallion.validateInputs(prices, currentPrice, simulations);
		SimulationResult result = runSimulations(adjustedVolatility, currentPrice, annualizedReturn, simulations);
		return buildDto(result, currentPrice, simulations);
	}

	@Override
	public List<SimulationPathDto> generateMonteCarloPaths(List<Double> prices, VolumeData volumeData,
			double currentPrice, int days, int simulations) {
		try {
			medallionAlgorithms.validatePrices(prices);
			medallionAlgorithms.validateVolumeData(volumeData);
			double annualVolatility = medallionAlgorithms.calculateVolatility(prices);
			VolumeData computedVolume = medallionAlgorithms.calculateVolume(volumeData);
			double volumeFactor = Math.min(computedVolume.getCurrVolume() / computedVolume.getAvgVolume(), 2.0);
			double adjustedVolatility = annualVolatility * volumeFactor;
			double sumLogReturns = 0;
			for (int i = 1; i < prices.size(); i++) {
				sumLogReturns += Math.log(prices.get(i) / prices.get(i - 1));
			}
			double avgDailyReturn = sumLogReturns / (prices.size() - 1);
			double annualizedReturn = avgDailyReturn * TRADING_DAYS_PER_YEAR;
			int batchSize = 1000;
			List<SimulationPathDto> results = new ArrayList<>(simulations);
			for (int start = 0; start < simulations; start += batchSize) {
				int currentBatchSize = Math.min(batchSize, simulations - start);
				List<SimulationPathDto> batchResults = simulationPool.submit(() -> IntStream
						.range(0, currentBatchSize).parallel().mapToObj(i -> medallionAlgorithms
								.simulatePricePathsMotecarlo(adjustedVolatility, currentPrice, days, annualizedReturn))
						.collect(Collectors.toList())).get();
				results.addAll(batchResults);
			}
			return results;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Monte Carlo path generation interrupted", e);
		} catch (ExecutionException e) {
			throw new RuntimeException("Monte Carlo path generation failed", e.getCause());
		}
	}

	private SimulationResult runSimulations(double adjustedVolatility, double currentPrice, double annualizedReturn,
			int simulations) {
		try {
			int batchSize = 1000;
			int remaining = simulations;
			List<Double> finalPrices = new ArrayList<>(simulations);
			while (remaining > 0) {
				int currentBatchSize = Math.min(batchSize, remaining);
				List<Double> batchResults = simulationPool.submit(() -> IntStream
						.range(0, currentBatchSize).parallel().mapToDouble(i -> medallionAlgorithms
								.getMedallionPrices(adjustedVolatility, currentPrice, annualizedReturn))
						.boxed().collect(Collectors.toList())).get();
				finalPrices.addAll(batchResults);
				remaining -= currentBatchSize;
			}
			finalPrices.sort(Double::compareTo);
			return buildSimulationResult(finalPrices, currentPrice);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Simulation interrupted", e);
		} catch (ExecutionException e) {
			throw new RuntimeException("Simulation failed", e.getCause());
		}
	}

	private SimulationResult buildSimulationResult(List<Double> sortedPrices, double currentPrice) {
		// Aggregate stats from already-sorted list
		long bullishCount = sortedPrices.stream().filter(p -> p > currentPrice).count();
		long bearishCount = sortedPrices.stream().filter(p -> p < currentPrice).count();
		long neutralCount = sortedPrices.stream().filter(p -> p == currentPrice).count();

		DoubleSummaryStatistics stats = sortedPrices.stream().mapToDouble(Double::doubleValue).summaryStatistics();

		double medianPrice = sortedPrices.get(sortedPrices.size() / 2);
		double spread = stats.getMax() - stats.getMin();
		double consistency = 1.0 - (spread / medianPrice);

		SimulationResult result = new SimulationResult();
		result.setBullishCount((int) bullishCount);
		result.setBearishCount((int) bearishCount);
		result.setNeutralCount((int) neutralCount);
		result.setTotalPrice(stats.getSum());
		result.setMedianPrice(medianPrice);
		result.setMinPrice(stats.getMin());
		result.setMaxPrice(stats.getMax());
		result.setSpread(spread);
		result.setConsistency(consistency);
		return result;
	}

	private DirectionalProbabilityDto buildDto(SimulationResult result, double currentPrice, int simulations) {
		double bullishProbability = Math.round(((double) result.getBullishCount() / simulations) * 100.0) / 100.0;
		double bearishProbability = Math.round(((double) result.getBearishCount() / simulations) * 100.0) / 100.0;
		double averagePrice = Math.round((result.getTotalPrice() / simulations) * 100.0) / 100.0;
		double expectedMovePercent = Math
				.round((((averagePrice - currentPrice) / currentPrice) * PERCENT_MULTIPLIER) * 100.0) / 100.0;
		/*
		 * Confidence Score
		 *
		 * Higher consistency + stronger directional agreement = stronger confidence
		 */
		double confidenceScore = (Math.abs(bullishProbability - bearishProbability) * result.getConsistency());

		DirectionalProbabilityDto dto = new DirectionalProbabilityDto();
		SingleDay singleCandle = new SingleDay();
		singleCandle.setBullishProbability(bullishProbability);
		singleCandle.setBearishProbability(bearishProbability);
		singleCandle.setNeutralProbability((double) result.getNeutralCount() / simulations);
		singleCandle.setAveragePrice(averagePrice);
		singleCandle.setBullishSimulations(result.getBullishCount());
		singleCandle.setBearishSimulations(result.getBearishCount());
		singleCandle.setMedianPrice(result.getMedianPrice());
		singleCandle.setMinPrice(result.getMinPrice());
		singleCandle.setMaxPrice(result.getMaxPrice());
		singleCandle.setCorridorSpread(result.getSpread());
		singleCandle.setSimulationConsistency(result.getConsistency());
		singleCandle.setConfidenceScore(confidenceScore);
		if (expectedMovePercent > 0) {
			singleCandle.setExpectedUpsidePercent(expectedMovePercent);
		} else {
			singleCandle.setExpectedDownsidePercent(Math.abs(expectedMovePercent));
		}
		dto.setOneday(singleCandle);
		return dto;
	}

}
