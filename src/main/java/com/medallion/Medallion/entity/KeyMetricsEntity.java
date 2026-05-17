package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "key_metrics")
public class KeyMetricsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> mgmtEffectiveness;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> margins;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> financialStrength;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> valuation;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> incomeStatement;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> growth;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> perShareData;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private List<MetricEntity> priceAndVolume;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MetricEntity> getMgmtEffectiveness() {
		return mgmtEffectiveness;
	}

	public void setMgmtEffectiveness(List<MetricEntity> mgmtEffectiveness) {
		this.mgmtEffectiveness = mgmtEffectiveness;
	}

	public List<MetricEntity> getMargins() {
		return margins;
	}

	public void setMargins(List<MetricEntity> margins) {
		this.margins = margins;
	}

	public List<MetricEntity> getFinancialStrength() {
		return financialStrength;
	}

	public void setFinancialStrength(List<MetricEntity> financialStrength) {
		this.financialStrength = financialStrength;
	}

	public List<MetricEntity> getValuation() {
		return valuation;
	}

	public void setValuation(List<MetricEntity> valuation) {
		this.valuation = valuation;
	}

	public List<MetricEntity> getIncomeStatement() {
		return incomeStatement;
	}

	public void setIncomeStatement(List<MetricEntity> incomeStatement) {
		this.incomeStatement = incomeStatement;
	}

	public List<MetricEntity> getGrowth() {
		return growth;
	}

	public void setGrowth(List<MetricEntity> growth) {
		this.growth = growth;
	}

	public List<MetricEntity> getPerShareData() {
		return perShareData;
	}

	public void setPerShareData(List<MetricEntity> perShareData) {
		this.perShareData = perShareData;
	}

	public List<MetricEntity> getPriceAndVolume() {
		return priceAndVolume;
	}

	public void setPriceAndVolume(List<MetricEntity> priceAndVolume) {
		this.priceAndVolume = priceAndVolume;
	}
}
