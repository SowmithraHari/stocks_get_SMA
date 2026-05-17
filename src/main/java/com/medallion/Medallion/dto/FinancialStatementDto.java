package com.medallion.Medallion.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialStatementDto {

	private List<FinancialCategoryDto> stockFinancials;

	@JsonProperty("FiscalYear")
	private String fiscalYear;

	@JsonProperty("EndDate")
	private String endDate;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("StatementDate")
	private String statementDate;

	private int fiscalPeriodNumber;

	public List<FinancialCategoryDto> getStockFinancials() {
		return stockFinancials;
	}

	public void setStockFinancials(List<FinancialCategoryDto> stockFinancials) {
		this.stockFinancials = stockFinancials;
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
