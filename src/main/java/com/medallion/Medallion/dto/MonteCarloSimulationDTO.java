package com.medallion.Medallion.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonteCarloSimulationDTO {

	private double currentPrice;
	private int simulationsRequested;
	private int simulationsSuccessful;
	private double meanPrice;
	private double medianPrice;
	private double stdDeviation;
	private double minPrice;
	private double maxPrice;
	private double percentile10;
	private double percentile25;
	private double percentile75;
	private double percentile90;
	private double probabilityOfGain;
	private double probabilityOfLoss;
	private double expectedReturnPct;
	private double valueAtRisk95;
	private double conditionalVaR;
	private String signal;
	private String computedAt;
	private List<String> warnings;

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getSimulationsRequested() {
		return simulationsRequested;
	}

	public void setSimulationsRequested(int simulationsRequested) {
		this.simulationsRequested = simulationsRequested;
	}

	public int getSimulationsSuccessful() {
		return simulationsSuccessful;
	}

	public void setSimulationsSuccessful(int simulationsSuccessful) {
		this.simulationsSuccessful = simulationsSuccessful;
	}

	public double getMeanPrice() {
		return meanPrice;
	}

	public void setMeanPrice(double meanPrice) {
		this.meanPrice = meanPrice;
	}

	public double getMedianPrice() {
		return medianPrice;
	}

	public void setMedianPrice(double medianPrice) {
		this.medianPrice = medianPrice;
	}

	public double getStdDeviation() {
		return stdDeviation;
	}

	public void setStdDeviation(double stdDeviation) {
		this.stdDeviation = stdDeviation;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getPercentile10() {
		return percentile10;
	}

	public void setPercentile10(double percentile10) {
		this.percentile10 = percentile10;
	}

	public double getPercentile25() {
		return percentile25;
	}

	public void setPercentile25(double percentile25) {
		this.percentile25 = percentile25;
	}

	public double getPercentile75() {
		return percentile75;
	}

	public void setPercentile75(double percentile75) {
		this.percentile75 = percentile75;
	}

	public double getPercentile90() {
		return percentile90;
	}

	public void setPercentile90(double percentile90) {
		this.percentile90 = percentile90;
	}

	public double getProbabilityOfGain() {
		return probabilityOfGain;
	}

	public void setProbabilityOfGain(double probabilityOfGain) {
		this.probabilityOfGain = probabilityOfGain;
	}

	public double getProbabilityOfLoss() {
		return probabilityOfLoss;
	}

	public void setProbabilityOfLoss(double probabilityOfLoss) {
		this.probabilityOfLoss = probabilityOfLoss;
	}

	public double getExpectedReturnPct() {
		return expectedReturnPct;
	}

	public void setExpectedReturnPct(double expectedReturnPct) {
		this.expectedReturnPct = expectedReturnPct;
	}

	public double getValueAtRisk95() {
		return valueAtRisk95;
	}

	public void setValueAtRisk95(double valueAtRisk95) {
		this.valueAtRisk95 = valueAtRisk95;
	}

	public double getConditionalVaR() {
		return conditionalVaR;
	}

	public void setConditionalVaR(double conditionalVaR) {
		this.conditionalVaR = conditionalVaR;
	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public String getComputedAt() {
		return computedAt;
	}

	public void setComputedAt(String computedAt) {
		this.computedAt = computedAt;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

}
