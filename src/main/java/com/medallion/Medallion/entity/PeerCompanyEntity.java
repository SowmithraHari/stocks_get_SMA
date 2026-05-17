package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "peer_company")
public class PeerCompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "price_to_book_value_ratio")
	private Double priceToBookValueRatio;

	@Column(name = "price_to_earnings_value_ratio")
	private Double priceToEarningsValueRatio;

	@Column(name = "market_cap")
	private Double marketCap;

	@Column(name = "price")
	private Double price;

	@Column(name = "percent_change")
	private Double percentChange;

	@Column(name = "net_change")
	private Double netChange;

	@Column(name = "return_on_average_equity_5_year_average")
	private Double returnOnAverageEquity5YearAverage;

	@Column(name = "return_on_average_equity_trailing_12_month")
	private Double returnOnAverageEquityTrailing12Month;

	@Column(name = "lt_debt_per_equity_most_recent_fiscal_year")
	private Double ltDebtPerEquityMostRecentFiscalYear;

	@Column(name = "net_profit_margin_5_year_average")
	private Double netProfitMargin5YearAverage;

	@Column(name = "net_profit_margin_percent_trailing_12_month")
	private Double netProfitMarginPercentTrailing12Month;

	@Column(name = "dividend_yield_indicated_annual_dividend")
	private Double dividendYieldIndicatedAnnualDividend;

	@Column(name = "total_shares_outstanding")
	private Double totalSharesOutstanding;

	@Column(name = "language_support")
	private String languageSupport;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "overall_rating")
	private String overallRating;

	@Column(name = "yhigh")
	private Double yhigh;

	@Column(name = "ylow")
	private Double ylow;

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

	public Double getPriceToBookValueRatio() {
		return priceToBookValueRatio;
	}

	public void setPriceToBookValueRatio(Double priceToBookValueRatio) {
		this.priceToBookValueRatio = priceToBookValueRatio;
	}

	public Double getPriceToEarningsValueRatio() {
		return priceToEarningsValueRatio;
	}

	public void setPriceToEarningsValueRatio(Double priceToEarningsValueRatio) {
		this.priceToEarningsValueRatio = priceToEarningsValueRatio;
	}

	public Double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}

	public Double getNetChange() {
		return netChange;
	}

	public void setNetChange(Double netChange) {
		this.netChange = netChange;
	}

	public Double getReturnOnAverageEquity5YearAverage() {
		return returnOnAverageEquity5YearAverage;
	}

	public void setReturnOnAverageEquity5YearAverage(Double returnOnAverageEquity5YearAverage) {
		this.returnOnAverageEquity5YearAverage = returnOnAverageEquity5YearAverage;
	}

	public Double getReturnOnAverageEquityTrailing12Month() {
		return returnOnAverageEquityTrailing12Month;
	}

	public void setReturnOnAverageEquityTrailing12Month(Double returnOnAverageEquityTrailing12Month) {
		this.returnOnAverageEquityTrailing12Month = returnOnAverageEquityTrailing12Month;
	}

	public Double getLtDebtPerEquityMostRecentFiscalYear() {
		return ltDebtPerEquityMostRecentFiscalYear;
	}

	public void setLtDebtPerEquityMostRecentFiscalYear(Double ltDebtPerEquityMostRecentFiscalYear) {
		this.ltDebtPerEquityMostRecentFiscalYear = ltDebtPerEquityMostRecentFiscalYear;
	}

	public Double getNetProfitMargin5YearAverage() {
		return netProfitMargin5YearAverage;
	}

	public void setNetProfitMargin5YearAverage(Double netProfitMargin5YearAverage) {
		this.netProfitMargin5YearAverage = netProfitMargin5YearAverage;
	}

	public Double getNetProfitMarginPercentTrailing12Month() {
		return netProfitMarginPercentTrailing12Month;
	}

	public void setNetProfitMarginPercentTrailing12Month(Double netProfitMarginPercentTrailing12Month) {
		this.netProfitMarginPercentTrailing12Month = netProfitMarginPercentTrailing12Month;
	}

	public Double getDividendYieldIndicatedAnnualDividend() {
		return dividendYieldIndicatedAnnualDividend;
	}

	public void setDividendYieldIndicatedAnnualDividend(Double dividendYieldIndicatedAnnualDividend) {
		this.dividendYieldIndicatedAnnualDividend = dividendYieldIndicatedAnnualDividend;
	}

	public Double getTotalSharesOutstanding() {
		return totalSharesOutstanding;
	}

	public void setTotalSharesOutstanding(Double totalSharesOutstanding) {
		this.totalSharesOutstanding = totalSharesOutstanding;
	}

	public String getLanguageSupport() {
		return languageSupport;
	}

	public void setLanguageSupport(String languageSupport) {
		this.languageSupport = languageSupport;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}

	public Double getYhigh() {
		return yhigh;
	}

	public void setYhigh(Double yhigh) {
		this.yhigh = yhigh;
	}

	public Double getYlow() {
		return ylow;
	}

	public void setYlow(Double ylow) {
		this.ylow = ylow;
	}
}
