package com.medallion.Medallion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticker {

	private double stockPrice;
	private double expectedReturn;
	private double annualVolatility;
	private double avgVolume;
	private double currVolume;
	private double delta;
	
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public double getExpectedReturn() {
		return expectedReturn;
	}
	public void setExpectedReturn(double expectedReturn) {
		this.expectedReturn = expectedReturn;
	}
	public double getAnnualVolatility() {
		return annualVolatility;
	}
	public void setAnnualVolatility(double annualVolatility) {
		this.annualVolatility = annualVolatility;
	}
	public double getAvgVolume() {
		return avgVolume;
	}
	public void setAvgVolume(double avgVolume) {
		this.avgVolume = avgVolume;
	}
	public double getCurrVolume() {
		return currVolume;
	}
	public void setCurrVolume(double currVolume) {
		this.currVolume = currVolume;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}

	
}
