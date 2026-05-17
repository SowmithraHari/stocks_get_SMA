package com.medallion.Medallion.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class StockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "industry")
	private String industry;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_profile_id")
	private CompanyProfileEntity companyProfile;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "current_price_id")
	private CurrentPriceEntity currentPrice;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_technical_data_id")
	private StockTechnicalDataEntity stockTechnicalData;

	@Column(name = "percent_change")
	private Double percentChange;

	@Column(name = "year_high")
	private Double yearHigh;

	@Column(name = "year_low")
	private Double yearLow;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "financials_id")
	private FinancialsEntity financials;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "key_metrics_id")
	private KeyMetricsEntity keyMetrics;

	@Column(name = "future_expiry_dates", columnDefinition = "TEXT")
	private String futureExpiryDates;

	@Column(name = "future_overview_data", columnDefinition = "TEXT")
	private String futureOverviewData;

	@Column(name = "initial_stock_financial_data", columnDefinition = "TEXT")
	private String initialStockFinancialData;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "analyst_view_id")
	private AnalystViewEntity analystView;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recos_bar_id")
	private RecosBarEntity recosBar;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "risk_meter_id")
	private RiskMeterEntity riskMeter;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shareholding_id")
	private ShareholdingEntity shareholding;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_corporate_action_data_id")
	private StockCorporateActionDataEntity stockCorporateActionData;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_details_reusable_data_id")
	private StockDetailsReusableDataEntity stockDetailsReusableData;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_financial_data_id")
	private StockFinancialDataEntity stockFinancialData;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recent_news_response_id")
	private RecentNewsResponseEntity recentNews;

	private LocalDate createdDate;

	@PrePersist
	public void setCreatedDate() {
		this.createdDate = LocalDate.now();
	}
	

	public FinancialsEntity getFinancials() {
		return financials;
	}


	public void setFinancials(FinancialsEntity financials) {
		this.financials = financials;
	}


	public StockFinancialDataEntity getStockFinancialData() {
		return stockFinancialData;
	}


	public void setStockFinancialData(StockFinancialDataEntity stockFinancialData) {
		this.stockFinancialData = stockFinancialData;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public CompanyProfileEntity getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(CompanyProfileEntity companyProfile) {
		this.companyProfile = companyProfile;
	}

	public CurrentPriceEntity getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPriceEntity currentPrice) {
		this.currentPrice = currentPrice;
	}

	public StockTechnicalDataEntity getStockTechnicalData() {
		return stockTechnicalData;
	}

	public void setStockTechnicalData(StockTechnicalDataEntity stockTechnicalData) {
		this.stockTechnicalData = stockTechnicalData;
	}

	public Double getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}

	public Double getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(Double yearHigh) {
		this.yearHigh = yearHigh;
	}

	public Double getYearLow() {
		return yearLow;
	}

	public void setYearLow(Double yearLow) {
		this.yearLow = yearLow;
	}

	public KeyMetricsEntity getKeyMetrics() {
		return keyMetrics;
	}

	public void setKeyMetrics(KeyMetricsEntity keyMetrics) {
		this.keyMetrics = keyMetrics;
	}

	public String getFutureExpiryDates() {
		return futureExpiryDates;
	}

	public void setFutureExpiryDates(String futureExpiryDates) {
		this.futureExpiryDates = futureExpiryDates;
	}

	public String getFutureOverviewData() {
		return futureOverviewData;
	}

	public void setFutureOverviewData(String futureOverviewData) {
		this.futureOverviewData = futureOverviewData;
	}

	public String getInitialStockFinancialData() {
		return initialStockFinancialData;
	}

	public void setInitialStockFinancialData(String initialStockFinancialData) {
		this.initialStockFinancialData = initialStockFinancialData;
	}

	public AnalystViewEntity getAnalystView() {
		return analystView;
	}

	public void setAnalystView(AnalystViewEntity analystView) {
		this.analystView = analystView;
	}

	public RecosBarEntity getRecosBar() {
		return recosBar;
	}

	public void setRecosBar(RecosBarEntity recosBar) {
		this.recosBar = recosBar;
	}

	public RiskMeterEntity getRiskMeter() {
		return riskMeter;
	}

	public void setRiskMeter(RiskMeterEntity riskMeter) {
		this.riskMeter = riskMeter;
	}

	public ShareholdingEntity getShareholding() {
		return shareholding;
	}

	public void setShareholding(ShareholdingEntity shareholding) {
		this.shareholding = shareholding;
	}

	public StockCorporateActionDataEntity getStockCorporateActionData() {
		return stockCorporateActionData;
	}

	public void setStockCorporateActionData(StockCorporateActionDataEntity stockCorporateActionData) {
		this.stockCorporateActionData = stockCorporateActionData;
	}

	public StockDetailsReusableDataEntity getStockDetailsReusableData() {
		return stockDetailsReusableData;
	}

	public void setStockDetailsReusableData(StockDetailsReusableDataEntity stockDetailsReusableData) {
		this.stockDetailsReusableData = stockDetailsReusableData;
	}

	public RecentNewsResponseEntity getRecentNews() {
		return recentNews;
	}

	public void setRecentNews(RecentNewsResponseEntity recentNews) {
		this.recentNews = recentNews;
	}
}