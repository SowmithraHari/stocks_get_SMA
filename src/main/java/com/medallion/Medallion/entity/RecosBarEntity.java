package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recos_bar")
public class RecosBarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recos_bar_id")
	private List<StockAnalystEntity> stockAnalyst;

	@Column(name = "ticker_rating_value")
	private int tickerRatingValue;

	@Column(name = "is_data_present")
	private boolean isDataPresent;

	@Column(name = "no_of_recommendations")
	private int noOfRecommendations;

	@Column(name = "mean_value")
	private double meanValue;

	@Column(name = "ticker_percentage")
	private double tickerPercentage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<StockAnalystEntity> getStockAnalyst() {
		return stockAnalyst;
	}

	public void setStockAnalyst(List<StockAnalystEntity> stockAnalyst) {
		this.stockAnalyst = stockAnalyst;
	}

	public int getTickerRatingValue() {
		return tickerRatingValue;
	}

	public void setTickerRatingValue(int tickerRatingValue) {
		this.tickerRatingValue = tickerRatingValue;
	}

	public boolean isDataPresent() {
		return isDataPresent;
	}

	public void setDataPresent(boolean dataPresent) {
		isDataPresent = dataPresent;
	}

	public int getNoOfRecommendations() {
		return noOfRecommendations;
	}

	public void setNoOfRecommendations(int noOfRecommendations) {
		this.noOfRecommendations = noOfRecommendations;
	}

	public double getMeanValue() {
		return meanValue;
	}

	public void setMeanValue(double meanValue) {
		this.meanValue = meanValue;
	}

	public double getTickerPercentage() {
		return tickerPercentage;
	}

	public void setTickerPercentage(double tickerPercentage) {
		this.tickerPercentage = tickerPercentage;
	}
}
