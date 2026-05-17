package com.medallion.Medallion.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_details_reusable_data")
public class StockDetailsReusableDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "close_price")
	private String close;

	@Column(name = "date_value")
	private String date;

	@Column(name = "time_value")
	private String time;

	@Column(name = "price")
	private String price;

	@Column(name = "percent_change")
	private String percentChange;

	@Column(name = "market_cap")
	private String marketCap;

	@Column(name = "yhigh")
	private String yhigh;

	@Column(name = "ylow")
	private String ylow;

	@Column(name = "high_price")
	private String high;

	@Column(name = "low_price")
	private String low;

	@Column(name = "p_per_e_basic_excluding_extraordinary_items_ttm")
	private String pPerEBasicExcludingExtraordinaryItemsTTM;

	@Column(name = "current_dividend_yield_common_stock_primary_issue_ltm")
	private String currentDividendYieldCommonStockPrimaryIssueLTM;

	@Column(name = "total_debt_per_total_equity_most_recent_quarter")
	private String totalDebtPerTotalEquityMostRecentQuarter;

	@Column(name = "price_ytd_price_percent_change")
	private String priceYTDPricePercentChange;

	@Column(name = "price_5_day_percent_change")
	private String price5DayPercentChange;

	@Column(name = "net_income")
	private String netIncome;

	@Column(name = "fiscal_year")
	private String fiscalYear;

	@Column(name = "interim_net_income")
	private String interimNetIncome;

	@Column(name = "sector_price_to_earnings_value_ratio")
	private String sectorPriceToEarningsValueRatio;

	@Column(name = "average_rating")
	private String averageRating;

	@Column(name = "promoter_share_holding")
	private String promoterShareHolding;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mutual_fund_share_holding_id")
	private MutualFundShareHoldingEntity mutualFundShareHolding;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public String getYhigh() {
		return yhigh;
	}

	public void setYhigh(String yhigh) {
		this.yhigh = yhigh;
	}

	public String getYlow() {
		return ylow;
	}

	public void setYlow(String ylow) {
		this.ylow = ylow;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getpPerEBasicExcludingExtraordinaryItemsTTM() {
		return pPerEBasicExcludingExtraordinaryItemsTTM;
	}

	public void setpPerEBasicExcludingExtraordinaryItemsTTM(String pPerEBasicExcludingExtraordinaryItemsTTM) {
		this.pPerEBasicExcludingExtraordinaryItemsTTM = pPerEBasicExcludingExtraordinaryItemsTTM;
	}

	public String getCurrentDividendYieldCommonStockPrimaryIssueLTM() {
		return currentDividendYieldCommonStockPrimaryIssueLTM;
	}

	public void setCurrentDividendYieldCommonStockPrimaryIssueLTM(
			String currentDividendYieldCommonStockPrimaryIssueLTM) {
		this.currentDividendYieldCommonStockPrimaryIssueLTM = currentDividendYieldCommonStockPrimaryIssueLTM;
	}

	public String getTotalDebtPerTotalEquityMostRecentQuarter() {
		return totalDebtPerTotalEquityMostRecentQuarter;
	}

	public void setTotalDebtPerTotalEquityMostRecentQuarter(String totalDebtPerTotalEquityMostRecentQuarter) {
		this.totalDebtPerTotalEquityMostRecentQuarter = totalDebtPerTotalEquityMostRecentQuarter;
	}

	public String getPriceYTDPricePercentChange() {
		return priceYTDPricePercentChange;
	}

	public void setPriceYTDPricePercentChange(String priceYTDPricePercentChange) {
		this.priceYTDPricePercentChange = priceYTDPricePercentChange;
	}

	public String getPrice5DayPercentChange() {
		return price5DayPercentChange;
	}

	public void setPrice5DayPercentChange(String price5DayPercentChange) {
		this.price5DayPercentChange = price5DayPercentChange;
	}

	public String getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getInterimNetIncome() {
		return interimNetIncome;
	}

	public void setInterimNetIncome(String interimNetIncome) {
		this.interimNetIncome = interimNetIncome;
	}

	public String getSectorPriceToEarningsValueRatio() {
		return sectorPriceToEarningsValueRatio;
	}

	public void setSectorPriceToEarningsValueRatio(String sectorPriceToEarningsValueRatio) {
		this.sectorPriceToEarningsValueRatio = sectorPriceToEarningsValueRatio;
	}

	public String getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	public String getPromoterShareHolding() {
		return promoterShareHolding;
	}

	public void setPromoterShareHolding(String promoterShareHolding) {
		this.promoterShareHolding = promoterShareHolding;
	}

	public MutualFundShareHoldingEntity getMutualFundShareHolding() {
		return mutualFundShareHolding;
	}

	public void setMutualFundShareHolding(MutualFundShareHoldingEntity mutualFundShareHolding) {
		this.mutualFundShareHolding = mutualFundShareHolding;
	}
}