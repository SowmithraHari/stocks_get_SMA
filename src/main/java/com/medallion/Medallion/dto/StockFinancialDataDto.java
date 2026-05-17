package com.medallion.Medallion.dto;

import java.util.List;

public class StockFinancialDataDto {

	 private List<StockFinancialYearDto> stockFinancialData;

	 public List<StockFinancialYearDto> getStockFinancialData() {
		 return stockFinancialData;
	 }

	 public void  setStockFinancialData(List<StockFinancialYearDto> stockFinancialData) {
		 this.stockFinancialData = stockFinancialData;
	 }
	 
	 
}
