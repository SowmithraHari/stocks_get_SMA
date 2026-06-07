package com.medallion.Medallion.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "data_set")
public class DatasetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Price price;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private VolumeDataEntity volumeData;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DMA200Entity dma200;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DMA50Enitity dma50;
	
	private String stockname;
	
	private String period;

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	private LocalDate createdDate;

	@PrePersist
	public void setCreatedDate() {
		this.createdDate = LocalDate.now();
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public VolumeDataEntity getVolumeData() {
		return volumeData;
	}

	public void setVolumeData(VolumeDataEntity volumeData) {
		this.volumeData = volumeData;
	}

	public DMA200Entity getDma200() {
		return dma200;
	}

	public void setDma200(DMA200Entity dma200) {
		this.dma200 = dma200;
	}

	public DMA50Enitity getDma50() {
		return dma50;
	}

	public void setDma50(DMA50Enitity dma50) {
		this.dma50 = dma50;
	}

}