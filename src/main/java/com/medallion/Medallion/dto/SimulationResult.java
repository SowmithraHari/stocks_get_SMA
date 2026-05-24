package com.medallion.Medallion.dto;

import java.util.List;

public class SimulationResult {

	private int bullishCount;
	private int bearishCount;
	private int neutralCount;
	private double totalPrice;
	private double medianPrice;

	private double minPrice;

	private double maxPrice;

	private double spread;

	private double consistency;

	private List<Double> finalPrices;

	public int getBullishCount() {
		return bullishCount;
	}

	public void setBullishCount(int bullishCount) {
		this.bullishCount = bullishCount;
	}

	public int getBearishCount() {
		return bearishCount;
	}

	public void setBearishCount(int bearishCount) {
		this.bearishCount = bearishCount;
	}

	public int getNeutralCount() {
		return neutralCount;
	}

	public void setNeutralCount(int neutralCount) {
		this.neutralCount = neutralCount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getMedianPrice() {
		return medianPrice;
	}

	public void setMedianPrice(double medianPrice) {
		this.medianPrice = medianPrice;
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

	public double getSpread() {
		return spread;
	}

	public void setSpread(double spread) {
		this.spread = spread;
	}

	public double getConsistency() {
		return consistency;
	}

	public void setConsistency(double consistency) {
		this.consistency = consistency;
	}

	public List<Double> getFinalPrices() {
		return finalPrices;
	}

	public void setFinalPrices(List<Double> finalPrices) {
		this.finalPrices = finalPrices;
	}
	
	
}
