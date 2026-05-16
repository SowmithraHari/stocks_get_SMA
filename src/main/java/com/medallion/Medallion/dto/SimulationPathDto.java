package com.medallion.Medallion.dto;

import java.util.List;

public class SimulationPathDto {

    private List<Double> prices;

    private double finalPrice;

    private boolean bullish;

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public boolean isBullish() {
        return bullish;
    }

    public void setBullish(boolean bullish) {
        this.bullish = bullish;
    }
}