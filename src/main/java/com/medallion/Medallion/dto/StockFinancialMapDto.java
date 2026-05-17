package com.medallion.Medallion.dto;

import java.util.List;

public class StockFinancialMapDto {
	
	private List<FinancialItemDto> CAS;
	private List<FinancialItemDto> BAL;
	private List<FinancialItemDto> INC;

	public List<FinancialItemDto> getCAS() {
		return CAS;
	}

	public void setCAS(List<FinancialItemDto> cAS) {
		CAS = cAS;
	}

	public List<FinancialItemDto> getBAL() {
		return BAL;
	}

	public void setBAL(List<FinancialItemDto> bAL) {
		BAL = bAL;
	}

	public List<FinancialItemDto> getINC() {
		return INC;
	}

	public void setINC(List<FinancialItemDto> iNC) {
		INC = iNC;
	}

	// getters and setters
}
