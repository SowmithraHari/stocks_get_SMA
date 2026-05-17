package com.medallion.Medallion.dto;

import java.util.List;

public class FinancialCategoryDto {

	private String category;

	private List<FinancialEntryDto> entries;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<FinancialEntryDto> getEntries() {
		return entries;
	}

	public void setEntries(List<FinancialEntryDto> entries) {
		this.entries = entries;
	}

}