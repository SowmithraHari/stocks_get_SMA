package com.medallion.Medallion.dto;

public class StockTechnicalDataDto {

	private Integer days;
	private Double bsePrice;
	private Double nsePrice;

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Double getBsePrice() {
		return bsePrice;
	}

	public void setBsePrice(Double bsePrice) {
		this.bsePrice = bsePrice;
	}

	public Double getNsePrice() {
		return nsePrice;
	}

	public void setNsePrice(Double nsePrice) {
		this.nsePrice = nsePrice;
	}

}
