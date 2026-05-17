package com.medallion.Medallion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockFinancialYearDto {
	
	@JsonProperty("stockFinancialMap")
    private StockFinancialMapDto stockFinancialMap;

    @JsonProperty("FiscalYear")
    private String fiscalYear;

    @JsonProperty("EndDate")
    private String endDate;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("StatementDate")
    private String statementDate;

    @JsonProperty("fiscalPeriodNumber")
    private int fiscalPeriodNumber;

	public StockFinancialMapDto getStockFinancialMap() {
		return stockFinancialMap;
	}

	public void setStockFinancialMap(StockFinancialMapDto stockFinancialMap) {
		this.stockFinancialMap = stockFinancialMap;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public int getFiscalPeriodNumber() {
		return fiscalPeriodNumber;
	}

	public void setFiscalPeriodNumber(int fiscalPeriodNumber) {
		this.fiscalPeriodNumber = fiscalPeriodNumber;
	}
}