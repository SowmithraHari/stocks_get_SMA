package com.medallion.Medallion.dto;

public class AnalystViewDto {

	private String colorCode;
	private String ratingName;
	private Integer ratingValue;
	private String numberOfAnalystsLatest;
	private String numberOfAnalysts1WeekAgo;
	private String numberOfAnalysts1MonthAgo;
	private String numberOfAnalysts2MonthAgo;
	private String numberOfAnalysts3MonthAgo;

	// Getters and Setters
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

	public Integer getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getNumberOfAnalystsLatest() {
		return numberOfAnalystsLatest;
	}

	public void setNumberOfAnalystsLatest(String numberOfAnalystsLatest) {
		this.numberOfAnalystsLatest = numberOfAnalystsLatest;
	}

	public String getNumberOfAnalysts1WeekAgo() {
		return numberOfAnalysts1WeekAgo;
	}

	public void setNumberOfAnalysts1WeekAgo(String numberOfAnalysts1WeekAgo) {
		this.numberOfAnalysts1WeekAgo = numberOfAnalysts1WeekAgo;
	}

	public String getNumberOfAnalysts1MonthAgo() {
		return numberOfAnalysts1MonthAgo;
	}

	public void setNumberOfAnalysts1MonthAgo(String numberOfAnalysts1MonthAgo) {
		this.numberOfAnalysts1MonthAgo = numberOfAnalysts1MonthAgo;
	}

	public String getNumberOfAnalysts2MonthAgo() {
		return numberOfAnalysts2MonthAgo;
	}

	public void setNumberOfAnalysts2MonthAgo(String numberOfAnalysts2MonthAgo) {
		this.numberOfAnalysts2MonthAgo = numberOfAnalysts2MonthAgo;
	}

	public String getNumberOfAnalysts3MonthAgo() {
		return numberOfAnalysts3MonthAgo;
	}

	public void setNumberOfAnalysts3MonthAgo(String numberOfAnalysts3MonthAgo) {
		this.numberOfAnalysts3MonthAgo = numberOfAnalysts3MonthAgo;
	}

}
