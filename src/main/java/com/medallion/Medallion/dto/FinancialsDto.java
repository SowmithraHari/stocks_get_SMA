package com.medallion.Medallion.dto;

import java.util.List;

public class FinancialsDto {

	private List<MetricDto> mgmtEffectiveness;
	private List<MetricDto> margins;
	private List<MetricDto> financialstrength;
	private List<MetricDto> valuation;
	private List<MetricDto> incomeStatement;
	private List<MetricDto> growth;
	private List<MetricDto> persharedata;
	private List<MetricDto> priceandVolume;
	
	
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
	public List<MetricDto> getFinancialstrength() {
		return financialstrength;
	}
	public void setFinancialstrength(List<MetricDto> financialstrength) {
		this.financialstrength = financialstrength;
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
	public List<MetricDto> getPersharedata() {
		return persharedata;
	}
	public void setPersharedata(List<MetricDto> persharedata) {
		this.persharedata = persharedata;
	}
	public List<MetricDto> getPriceandVolume() {
		return priceandVolume;
	}
	public void setPriceandVolume(List<MetricDto> priceandVolume) {
		this.priceandVolume = priceandVolume;
	}
	
	

}
