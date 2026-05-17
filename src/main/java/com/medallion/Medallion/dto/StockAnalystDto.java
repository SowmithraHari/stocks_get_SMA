package com.medallion.Medallion.dto;

public class StockAnalystDto {

	private String colorCode;
	private String ratingName;
	private int ratingValue;
	private double minValue;
	private double maxValue;
	private int numberOfAnalysts;

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getRatingName() {
		return ratingName;
	}

	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	public int getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(int ratingValue) {
		this.ratingValue = ratingValue;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public int getNumberOfAnalysts() {
		return numberOfAnalysts;
	}

	public void setNumberOfAnalysts(int numberOfAnalysts) {
		this.numberOfAnalysts = numberOfAnalysts;
	}

}
