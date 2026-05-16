package com.medallion.Medallion.dematserviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.medallion.Medallion.dto.SimulationPathDto;
import com.medallion.Medallion.dto.VolumeData;
import com.medallion.Medallion.dto.VolumeEntryDTO;

@Component
public class MedallionAlgorithms {

	private static final Logger logger = LoggerFactory.getLogger(MedallionAlgorithms.class);
	private static final int TRADING_DAYS_PER_YEAR = 252;
	private static final double DELTA = 1.0 / TRADING_DAYS_PER_YEAR;
	private final Random random;

	public MedallionAlgorithms() {
		this.random = new Random();
	}

	MedallionAlgorithms(Random random) {
		this.random = random;
	}

	/**
	 * Computes the next-day Medallion price using a Geometric Brownian Motion
	 * model.
	 *
	 * @param prices       Historical closing prices (at least 2 entries, oldest →
	 *                     newest)
	 * @param volumeData   Volume data containing a non-empty list of
	 *                     VolumeEntryDTOs
	 * @param currentPrice The current/latest price (must be > 0)
	 * @return Projected next-day price
	 * @throws IllegalArgumentException  if inputs are invalid
	 * @throws MedallionPricingException if computation fails unexpectedly
	 */
	public double getMedallionPrice(List<Double> prices, VolumeData volumeData, double currentPrice) {
		validatePrices(prices);
		validateVolumeData(volumeData);
		if (currentPrice <= 0) {
			throw new IllegalArgumentException("currentPrice must be positive, got: " + currentPrice);
		}
		try {
			// Annualized volatility from 6-month price history
			double annualVolatility = calculateVolatility(prices);
			VolumeData computedVolume = calculateVolume(volumeData);
			// double adjVolatility = annualVolatility * (computedVolume.getCurrVolume() /
			// computedVolume.getAvgVolume());
			double volumeFactor = Math.min(computedVolume.getCurrVolume() / computedVolume.getAvgVolume(), 2.0);
			double adjVolatility = annualVolatility * volumeFactor;
			// Expected annualized return from log price ratio
			double[] priceArray = prices.stream().mapToDouble(Double::doubleValue).toArray();
			int tradingDays = priceArray.length;
			double totalLogReturn = Math.log(priceArray[tradingDays - 1] / priceArray[0]);
			double annualizedReturn = (totalLogReturn / tradingDays) * TRADING_DAYS_PER_YEAR;
			// GBM: S(t+Δ) = S(t) * exp((μ - σ²/2)Δ + σ√Δ * Z)
			double z = random.nextGaussian();
			double exponent = (annualizedReturn - 0.5 * adjVolatility * adjVolatility) * DELTA
					+ adjVolatility * Math.sqrt(DELTA) * z;

			return currentPrice * Math.exp(exponent);
		} catch (IllegalArgumentException | MedallionPricingException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error computing Medallion price for currentPrice={}", currentPrice, e);
			throw new MedallionPricingException("Price computation failed unexpectedly", e);
		}
	}

	/**
	 * Calculates annualized historical volatility from a list of closing prices
	 * using log returns and sample standard deviation, scaled by √252.
	 *
	 * @param prices At least 2 closing prices (oldest → newest)
	 * @return Annualized volatility (σ)
	 * @throws IllegalArgumentException  if prices are invalid
	 * @throws MedallionPricingException if computation fails unexpectedly
	 */
	public double calculateVolatility(List<Double> prices) {
		validatePrices(prices);
		try {
			List<Double> logReturns = new ArrayList<>(prices.size() - 1);
			for (int i = 1; i < prices.size(); i++) {
				double prev = prices.get(i - 1);
				double curr = prices.get(i);
				if (prev <= 0 || curr <= 0) {
					throw new IllegalArgumentException(
							"All prices must be positive. Found prev=" + prev + ", curr=" + curr);
				}
				logReturns.add(Math.log(curr / prev));
			}
			double mean = logReturns.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
			double variance = 0.0;
			for (double r : logReturns) {
				variance += Math.pow(r - mean, 2);
			}
			variance /= (logReturns.size() - 1);
			double dailyVolatility = Math.sqrt(variance);
			return dailyVolatility * Math.sqrt(TRADING_DAYS_PER_YEAR);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error calculating volatility", e);
			throw new MedallionPricingException("Volatility calculation failed unexpectedly", e);
		}
	}

	/**
	 * Computes the average volume and identifies the most recent volume entry.
	 * Returns a NEW VolumeData — the original is never mutated.
	 *
	 * @param volumeData Source volume data with a non-empty list of entries
	 * @return A new VolumeData with avgVolume and currVolume populated
	 * @throws IllegalArgumentException  if volumeData or its entries are null/empty
	 * @throws MedallionPricingException if no entries are found after streaming
	 */
	public VolumeData calculateVolume(VolumeData volumeData) {
		validateVolumeData(volumeData);

		List<VolumeEntryDTO> entries = volumeData.getValues();

		double average = entries.stream().mapToLong(VolumeEntryDTO::getVolume).average().orElse(0.0);

		VolumeEntryDTO latest = entries.stream().max(Comparator.comparing(e -> LocalDate.parse(e.getDate())))
				.orElseThrow(() -> new MedallionPricingException("No volume entries found"));

		VolumeData result = new VolumeData();
		result.setValues(entries);
		result.setAvgVolume(average);
		result.setCurrVolume(latest.getVolume());
		return result;
	}

	/**
	 * Simulates a future price path using Geometric Brownian Motion (GBM), adjusted
	 * for volume-based volatility scaling.
	 *
	 * @param prices       Historical price data used to compute volatility and
	 *                     drift
	 * @param volumeData   Current and average volume data used to scale volatility
	 * @param currentPrice The starting price for the simulation
	 * @param days         Number of trading days to simulate
	 * @return A {@link SimulationPathDto} containing the simulated price path,
	 *         final price, and a bullish/bearish signal
	 * @throws IllegalArgumentException if prices or volumeData are invalid, or days
	 *                                  <= 0
	 */
	public SimulationPathDto simulatePricePath(List<Double> prices, VolumeData volumeData, double currentPrice,
			int days) {
		validatePrices(prices);
		validateVolumeData(volumeData);
		if (days <= 0) {
			throw new IllegalArgumentException("days must be positive");
		}
		// Step 1: Compute annualized historical volatility from price series
		double annualVolatility = calculateVolatility(prices);
		// Step 2: Compute volume metrics (e.g., rolling average) from raw volume data
		VolumeData computedVolume = calculateVolume(volumeData);
		// Step 3: Derive a volume factor capped at 2.0 to avoid extreme amplification.
		// A ratio > 1 means current volume exceeds average — higher activity → higher
		// volatility.
		double volumeFactor = Math.min(computedVolume.getCurrVolume() / computedVolume.getAvgVolume(), 2.0);
		// Step 4: Scale volatility by the volume factor to reflect market activity
		double adjustedVolatility = annualVolatility * volumeFactor;
		double[] priceArray = prices.stream().mapToDouble(Double::doubleValue).toArray();
		int tradingDays = priceArray.length;
		// Step 5: Compute the total log return over the historical price window.
		// Log returns are used because they are additive and suit GBM assumptions.
		double totalLogReturn = Math.log(priceArray[tradingDays - 1] / priceArray[0]);
		// Step 6: Annualize the average daily log return by scaling to
		// TRADING_DAYS_PER_YEAR
		double annualizedReturn = (totalLogReturn / tradingDays) * TRADING_DAYS_PER_YEAR;
		List<Double> simulatedPrices = new ArrayList<>();
		double simulatedPrice = currentPrice;
		// Step 7: Simulate price path using the GBM discretization formula:
		// S(t+Δt) = S(t) * exp((μ - 0.5σ²)Δt + σ√Δt * Z)
		// where:
		// μ = annualized drift (annualizedReturn)
		// σ = adjusted volatility
		// Δt = time step (DELTA, typically 1/252 for daily)
		// Z ~ N(0,1) standard normal random variable
		for (int i = 0; i < days; i++) {
			// Draw a standard normal random variable for this time step
			double z = random.nextGaussian();
			// Compute the GBM exponent: drift term + diffusion term
			// The -0.5σ² adjustment (Itô correction) converts from log-normal to real-world
			// drift
			double exponent = (annualizedReturn - 0.5 * adjustedVolatility * adjustedVolatility) * DELTA
					+ adjustedVolatility * Math.sqrt(DELTA) * z;
			// Apply the exponent to evolve the price forward by one time step
			simulatedPrice = simulatedPrice * Math.exp(exponent);
			simulatedPrices.add(simulatedPrice);
		}
		// Step 8: Package results into the DTO
		SimulationPathDto dto = new SimulationPathDto();
		dto.setPrices(simulatedPrices); // Full simulated price path
		dto.setFinalPrice(simulatedPrice); // Terminal price at end of simulation
		dto.setBullish(simulatedPrice > currentPrice); // True if price appreciated overall
		return dto;
	}

	private void validatePrices(List<Double> prices) {
		if (prices == null || prices.size() < 2) {
			throw new IllegalArgumentException(
					"prices must contain at least 2 entries, got: " + (prices == null ? "null" : prices.size()));
		}
	}

	private void validateVolumeData(VolumeData volumeData) {
		if (volumeData == null) {
			throw new IllegalArgumentException("volumeData must not be null");
		}
		if (volumeData.getValues() == null || volumeData.getValues().isEmpty()) {
			throw new IllegalArgumentException("volumeData must contain at least one entry");
		}
	}
}