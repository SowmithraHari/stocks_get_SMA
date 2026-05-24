package com.medallion.Medallion.dematserviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private MedallionAlgorithms medallionAlgorithms;

	@Autowired
	private ValidateMedallion validateMedallion;

	// ── Run simulations ──────────────────────────────────────────────────
	private static final int PERCENT_MULTIPLIER = 100;

	@Override
	public DirectionalProbabilityDto calculateDirectionalProbability(List<Double> prices, VolumeData volumeData,
			double currentPrice, int simulations) {
		validateMedallion.validateInputs(prices, currentPrice, simulations);
		SimulationResult result = runSimulations(prices, volumeData, currentPrice, simulations);
		return buildDto(result, currentPrice, simulations);
	}

	@Override
	public List<SimulationPathDto> generateMonteCarloPaths(List<Double> prices, VolumeData volumeData,
			double currentPrice, int days, int simulations) {
		List<SimulationPathDto> paths = new ArrayList<>();
		for (int i = 0; i < simulations; i++) {
			SimulationPathDto path = medallionAlgorithms.simulatePricePath(prices, volumeData, currentPrice, days);
			paths.add(path);
		}
		return paths;
	}

	private SimulationResult runSimulations(List<Double> prices, VolumeData volumeData, double currentPrice,
			int simulations) {

		int bullishCount = 0;
		int bearishCount = 0;
		int neutralCount = 0;
		double totalPrice = 0.0;
		List<Double> finalPrices = new ArrayList<>();

		for (int i = 0; i < simulations; i++) {
			double simulatedPrice = medallionAlgorithms.getMedallionPrice(prices, volumeData, currentPrice);
			totalPrice += simulatedPrice;
			finalPrices.add(simulatedPrice);
			if (simulatedPrice > currentPrice) {
				bullishCount++;
			} else if (simulatedPrice < currentPrice) {
				bearishCount++;
			} else {
				neutralCount++;
			}
		}
		Collections.sort(finalPrices);
		double medianPrice = finalPrices.get(finalPrices.size() / 2);
		double minPrice = finalPrices.get(0);
		double maxPrice = finalPrices.get(finalPrices.size() - 1);
		double spread = maxPrice - minPrice;
		/*
		 * Measures simulation agreement.
		 *
		 * Lower spread relative to median means higher confidence.
		 */
		double consistency = 1.0 - (spread / medianPrice);

		SimulationResult result = new SimulationResult();
		result.setBearishCount(bearishCount);
		result.setBullishCount(bullishCount);
		result.setTotalPrice(totalPrice);
		result.setNeutralCount(neutralCount);
		result.setMedianPrice(medianPrice);
		result.setMinPrice(minPrice);
		result.setMaxPrice(maxPrice);
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
