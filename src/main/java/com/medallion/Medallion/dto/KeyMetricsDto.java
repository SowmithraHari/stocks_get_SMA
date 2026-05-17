package com.medallion.Medallion.dto;

import java.util.List;

public class KeyMetricsDto {

	private List<MetricDto> mgmtEffectiveness;
	private List<MetricDto> margins;
	private List<MetricDto> financialStrength;
	private List<MetricDto> valuation;
	private List<MetricDto> incomeStatement;
	private List<MetricDto> growth;
	private List<MetricDto> perShareData;
	private List<MetricDto> priceAndVolume;

	public List<MetricDto> getMgmtEffectiveness() {
		return mgmtEffectiveness;
	}

	public void setMgmtEffectiveness(List<MetricDto> mgmtEffectiveness) {
		this.mgmtEffectiveness = mgmtEffectiveness;
	}

	public List<MetricDto> getMargins() {
		return margins;
	}

	public void setMargins(List<MetricDto> margins) {
		this.margins = margins;
	}

	public List<MetricDto> getFinancialStrength() {
		return financialStrength;
	}

	public void setFinancialStrength(List<MetricDto> financialStrength) {
		this.financialStrength = financialStrength;
	}

	public List<MetricDto> getValuation() {
		return valuation;
	}

	public void setValuation(List<MetricDto> valuation) {
		this.valuation = valuation;
	}

	public List<MetricDto> getIncomeStatement() {
		return incomeStatement;
	}

	public void setIncomeStatement(List<MetricDto> incomeStatement) {
		this.incomeStatement = incomeStatement;
	}

	public List<MetricDto> getGrowth() {
		return growth;
	}

	public void setGrowth(List<MetricDto> growth) {
		this.growth = growth;
	}

	public List<MetricDto> getPerShareData() {
		return perShareData;
	}

	public void setPerShareData(List<MetricDto> perShareData) {
		this.perShareData = perShareData;
	}

	public List<MetricDto> getPriceAndVolume() {
		return priceAndVolume;
	}

	public void setPriceAndVolume(List<MetricDto> priceAndVolume) {
		this.priceAndVolume = priceAndVolume;
	}

}
