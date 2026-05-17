package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_analyst")
public class StockAnalystEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "color_code")
	private String colorCode;

	@Column(name = "rating_name")
	private String ratingName;

	@Column(name = "rating_value")
	private int ratingValue;

	@Column(name = "min_value")
	private double minValue;

	@Column(name = "max_value")
	private double maxValue;

	@Column(name = "number_of_analysts")
	private int numberOfAnalysts;

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