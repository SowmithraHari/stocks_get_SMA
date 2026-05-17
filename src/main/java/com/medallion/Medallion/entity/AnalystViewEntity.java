package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "analyst_view")
public class AnalystViewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "color_code")
	private String colorCode;

	@Column(name = "rating_name")
	private String ratingName;

	@Column(name = "rating_value")
	private Integer ratingValue;

	@Column(name = "number_of_analysts_latest")
	private String numberOfAnalystsLatest;

	@Column(name = "number_of_analysts_1_week_ago")
	private String numberOfAnalysts1WeekAgo;

	@Column(name = "number_of_analysts_1_month_ago")
	private String numberOfAnalysts1MonthAgo;

	@Column(name = "number_of_analysts_2_month_ago")
	private String numberOfAnalysts2MonthAgo;

	@Column(name = "number_of_analysts_3_month_ago")
	private String numberOfAnalysts3MonthAgo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
