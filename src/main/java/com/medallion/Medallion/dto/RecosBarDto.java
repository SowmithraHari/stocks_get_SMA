package com.medallion.Medallion.dto;

import java.util.List;

public class RecosBarDto {
	
	 private List<StockAnalystDto> stockAnalyst;
	    private int tickerRatingValue;
	    private boolean isDataPresent;
	    private int noOfRecommendations;
	    private double meanValue;
	    private double tickerPercentage;

	    public List<StockAnalystDto> getStockAnalyst() {
	        return stockAnalyst;
	    }

	    public void setStockAnalyst(List<StockAnalystDto> stockAnalyst) {
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
