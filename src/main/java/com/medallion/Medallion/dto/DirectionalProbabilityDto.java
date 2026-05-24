package com.medallion.Medallion.dto;

import java.util.List;

public class DirectionalProbabilityDto {

	private SingleDay oneday;

	List<SimulationPathDto> multidays;
	
	private DataSetDto dataSetDto;
	
	private double medianPrice;

	private double minPrice;

	private double maxPrice;

	private double priceSpread;

	private double confidenceScore;

	private double bullishProbability;
	
	
	

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

	public double getPriceSpread() {
		return priceSpread;
	}

	public void setPriceSpread(double priceSpread) {
		this.priceSpread = priceSpread;
	}

	public double getConfidenceScore() {
		return confidenceScore;
	}

	public void setConfidenceScore(double confidenceScore) {
		this.confidenceScore = confidenceScore;
	}

	public double getBullishProbability() {
		return bullishProbability;
	}

	public void setBullishProbability(double bullishProbability) {
		this.bullishProbability = bullishProbability;
	}

	public SingleDay getOneday() {
		return oneday;
	}

	public void setOneday(SingleDay oneday) {
		this.oneday = oneday;
	}

	public List<SimulationPathDto> getMultidays() {
		return multidays;
	}

	public void setMultidays(List<SimulationPathDto> multidays) {
		this.multidays = multidays;
	}

	public DataSetDto getDataSetDto() {
		return dataSetDto;
	}

	public void setDataSetDto(DataSetDto dataSetDto) {
		this.dataSetDto = dataSetDto;
	}


}