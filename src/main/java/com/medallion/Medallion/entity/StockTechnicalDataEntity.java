package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_technical_data")
public class StockTechnicalDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "days")
	private Integer days;

	@Column(name = "bse_price")
	private Double bsePrice;

	@Column(name = "nse_price")
	private Double nsePrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
