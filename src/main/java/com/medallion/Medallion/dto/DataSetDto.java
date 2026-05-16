package com.medallion.Medallion.dto;

import java.time.LocalDate;

public class DataSetDto {

	private PriceDto price;

	private VolumeData volumeData;

	private DMA200Dto dma200;

	private DMA50Dto dma50;

	private LocalDate createdDate;
	private String stockname;

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public PriceDto getPrice() {
		return price;
	}

	public void setPrice(PriceDto price) {
		this.price = price;
	}

	public VolumeData getVolumeData() {
		return volumeData;
	}

	public void setVolumeData(VolumeData volumeData) {
		this.volumeData = volumeData;
	}

	public DMA200Dto getDma200() {
		return dma200;
	}

	public void setDma200(DMA200Dto dma200) {
		this.dma200 = dma200;
	}

	public DMA50Dto getDma50() {
		return dma50;
	}

	public void setDma50(DMA50Dto dma50) {
		this.dma50 = dma50;
	}

}
