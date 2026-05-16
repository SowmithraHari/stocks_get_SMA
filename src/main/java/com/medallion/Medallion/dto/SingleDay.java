package com.medallion.Medallion.dto;

public class SingleDay {
	
	private double bullishProbability;
	private double bearishProbability;

	private double averagePrice;

	private double expectedUpsidePercent;
	private double expectedDownsidePercent;

	private int bullishSimulations;
	private int bearishSimulations;
	
	private double neutralProbability;

	public double getBullishProbability() {
		return bullishProbability;
	}

	public void setBullishProbability(double bullishProbability) {
		this.bullishProbability = bullishProbability;
	}

	public double getBearishProbability() {
		return bearishProbability;
	}

	public void setBearishProbability(double bearishProbability) {
		this.bearishProbability = bearishProbability;
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public double getExpectedUpsidePercent() {
		return expectedUpsidePercent;
	}

	public void setExpectedUpsidePercent(double expectedUpsidePercent) {
		this.expectedUpsidePercent = expectedUpsidePercent;
	}

	public double getExpectedDownsidePercent() {
		return expectedDownsidePercent;
	}

	public void setExpectedDownsidePercent(double expectedDownsidePercent) {
		this.expectedDownsidePercent = expectedDownsidePercent;
	}

	public int getBullishSimulations() {
		return bullishSimulations;
	}

	public void setBullishSimulations(int bullishSimulations) {
		this.bullishSimulations = bullishSimulations;
	}

	public int getBearishSimulations() {
		return bearishSimulations;
	}

	public void setBearishSimulations(int bearishSimulations) {
		this.bearishSimulations = bearishSimulations;
	}

	public double getNeutralProbability() {
		return neutralProbability;
	}

	public void setNeutralProbability(double neutralProbability) {
		this.neutralProbability = neutralProbability;
	}
	
	

}
