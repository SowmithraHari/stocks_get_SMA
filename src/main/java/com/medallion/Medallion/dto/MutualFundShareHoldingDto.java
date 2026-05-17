package com.medallion.Medallion.dto;

public class MutualFundShareHoldingDto {

    private String holdingDate;
    private String percentage;

    public String getHoldingDate() {
        return holdingDate;
    }

    public void setHoldingDate(String holdingDate) {
        this.holdingDate = holdingDate;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}