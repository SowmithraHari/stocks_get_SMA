package com.medallion.Medallion.apiservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.medallion.Medallion.dto.AnalystViewDto;
import com.medallion.Medallion.dto.AnnualGeneralMeetingDto;
import com.medallion.Medallion.dto.BoardMeetingDto;
import com.medallion.Medallion.dto.CompanyProfileDto;
import com.medallion.Medallion.dto.CurrentPriceDto;
import com.medallion.Medallion.dto.DMA200Dto;
import com.medallion.Medallion.dto.DMA50Dto;
import com.medallion.Medallion.dto.DataSetDto;
import com.medallion.Medallion.dto.DividendDto;
import com.medallion.Medallion.dto.FinancialCategoryDto;
import com.medallion.Medallion.dto.FinancialEntryDto;
import com.medallion.Medallion.dto.FinancialItemDto;
import com.medallion.Medallion.dto.FinancialStatementDto;
import com.medallion.Medallion.dto.FinancialsDto;
import com.medallion.Medallion.dto.ImageWrapperDto;
import com.medallion.Medallion.dto.KeyMetricsDto;
import com.medallion.Medallion.dto.LeadMediaDto;
import com.medallion.Medallion.dto.MetadataDto;
import com.medallion.Medallion.dto.MetricDto;
import com.medallion.Medallion.dto.MutualFundShareHoldingDto;
import com.medallion.Medallion.dto.NewsImageDto;
import com.medallion.Medallion.dto.OfficerDto;
import com.medallion.Medallion.dto.PeerCompanyDto;
import com.medallion.Medallion.dto.PriceDto;
import com.medallion.Medallion.dto.RecentNewsDto;
import com.medallion.Medallion.dto.RecentNewsResponseDto;
import com.medallion.Medallion.dto.RecosBarDto;
import com.medallion.Medallion.dto.RiskMeterDto;
import com.medallion.Medallion.dto.ShareholdingCategoryDto;
import com.medallion.Medallion.dto.ShareholdingDto;
import com.medallion.Medallion.dto.SplitDto;
import com.medallion.Medallion.dto.StockAnalystDto;
import com.medallion.Medallion.dto.StockCorporateActionDataDto;
import com.medallion.Medallion.dto.StockDetailsReusableDataDto;
import com.medallion.Medallion.dto.StockDto;
import com.medallion.Medallion.dto.StockFinancialDataDto;
import com.medallion.Medallion.dto.StockFinancialMapDto;
import com.medallion.Medallion.dto.StockFinancialYearDto;
import com.medallion.Medallion.dto.StockTechnicalDataDto;
import com.medallion.Medallion.dto.TitleDto;
import com.medallion.Medallion.dto.ValueDto;
import com.medallion.Medallion.dto.VolumeData;
import com.medallion.Medallion.dto.VolumeEntryDTO;

@Component
public class ApiResponses {

	public StockDto formStockDto(String data) {
		JSONObject json = new JSONObject(data);

		StockDto stockDto = new StockDto();

		stockDto.setCompanyName(json.optString("companyName"));
		stockDto.setIndustry(json.optString("industry"));

		if (json.has("companyProfile")) {
			stockDto.setCompanyProfile(formCompanyProfile(data));
		}

		if (json.has("currentPrice")) {
			stockDto.setCurrentPrice(formCurrentPrice(data));
		}

		if (json.has("stockTechnicalData")) {
			List<StockTechnicalDataDto> technicalData = formStockTechnicalData(data);
			if (technicalData != null && !technicalData.isEmpty()) {
				stockDto.setStockTechnicalData(technicalData.get(0));
			}
		}

		stockDto.setPercentChange(json.optDouble("percentChange"));
		stockDto.setYearHigh(json.optDouble("yearHigh"));
		stockDto.setYearLow(json.optDouble("yearLow"));

		if (json.has("financials")) {
			FinancialsDto financialsDto = formFinancials(data);
			if (financialsDto != null) {
				stockDto.setFinancials(financialsDto);
			}
		}

		if (json.has("keyMetrics") && !json.isNull("keyMetrics")) {
			stockDto.setKeyMetrics(formKeyMetrics(data));
		}

		stockDto.setFutureExpiryDates(json.optString("futureExpiryDates"));
		stockDto.setFutureOverviewData(json.optString("futureOverviewData"));
		stockDto.setInitialStockFinancialData(json.optString("initialStockFinancialData"));

		if (json.has("analystView")) {
			List<AnalystViewDto> analystViews = formAnalystView(data);
			if (analystViews != null && !analystViews.isEmpty()) {
				stockDto.setAnalystView(analystViews.get(0));
			}
		}

		if (json.has("recosBar")) {
			stockDto.setRecosBar(formRecosBar(data));
		}

		if (json.has("riskMeter")) {
			stockDto.setRiskMeter(formRiskMeter(data));
		}

		if (json.has("shareholding")) {
			List<ShareholdingDto> shareholdingList = formShareholding(data);
			if (shareholdingList != null && !shareholdingList.isEmpty()) {
				stockDto.setShareholding(shareholdingList.get(0));
			}
		}

		if (json.has("stockCorporateActionData")) {
			stockDto.setStockCorporateActionData(formStockCorporateActionData(data));
		}

		if (json.has("stockDetailsReusableData")) {
			stockDto.setStockDetailsReusableData(formStockDetailsReusableData(data));
		}

		if (json.has("stockFinancialData")) {
			stockDto.setStockFinancialData(formStockFinancialData(data));
		}

		if (json.has("recentNews")) {
			stockDto.setRecentNews(formRecentNews(data));
		}

		return stockDto;
	}

	public CompanyProfileDto formCompanyProfile(String data) {

		JSONObject json = new JSONObject(data);
		JSONObject companyProfileJson = json.optJSONObject("companyProfile");

		CompanyProfileDto companyProfileDto = new CompanyProfileDto();

		if (companyProfileJson == null) {
			return companyProfileDto;
		}

		companyProfileDto.setCompanyDescription(companyProfileJson.optString("companyDescription"));
		companyProfileDto.setMgIndustry(companyProfileJson.optString("mgIndustry"));
		companyProfileDto.setIsinId(companyProfileJson.optString("isInId"));
		companyProfileDto.setExchangeCodeBse(companyProfileJson.optString("exchangeCodeBse"));
		companyProfileDto.setExchangeCodeNse(companyProfileJson.optString("exchangeCodeNse"));

		List<OfficerDto> officerDtos = new ArrayList<>();

		JSONObject officersObject = companyProfileJson.optJSONObject("officers");

		if (officersObject != null) {

			JSONArray officersArray = officersObject.optJSONArray("officer");

			if (officersArray != null) {

				for (int i = 0; i < officersArray.length(); i++) {

					JSONObject officerJson = officersArray.optJSONObject(i);

					if (officerJson == null) {
						continue;
					}

					OfficerDto officerDto = new OfficerDto();

					officerDto.setRank(officerJson.optInt("rank"));
					officerDto.setSince(officerJson.optString("since"));
					officerDto.setFirstName(officerJson.optString("firstName"));
					officerDto.setmI(officerJson.optString("mI"));
					officerDto.setLastName(officerJson.optString("lastName"));

					String age = officerJson.optString("age");

					if (age != null && !age.isBlank()) {
						officerDto.setAge(Integer.valueOf(age));
					}

					JSONObject titleJson = officerJson.optJSONObject("title");

					if (titleJson != null) {

						TitleDto titleDto = new TitleDto();

						titleDto.setStartYear(titleJson.optString("startYear"));
						titleDto.setStartMonth(titleJson.optString("startMonth"));
						titleDto.setStartDay(titleJson.optString("startDay"));

						titleDto.setId1(titleJson.optString("iD1"));
						titleDto.setAbbr1(titleJson.optString("abbr1"));
						titleDto.setId2(titleJson.optString("iD2"));
						titleDto.setAbbr2(titleJson.optString("abbr2"));
						titleDto.setValue(titleJson.optString("Value"));

						officerDto.setTitle(titleDto);
					}

					officerDtos.add(officerDto);
				}
			}
		}

		companyProfileDto.setOfficers(officerDtos);

		List<PeerCompanyDto> peerCompanyDtos = new ArrayList<>();

		JSONArray peerCompanyArray = companyProfileJson.optJSONArray("peerCompanyList");

		if (peerCompanyArray != null) {

			peerCompanyDtos = IntStream.range(0, peerCompanyArray.length()).mapToObj(peerCompanyArray::optJSONObject)
					.filter(Objects::nonNull).map(peerJson -> {

						PeerCompanyDto peerDto = new PeerCompanyDto();

						peerDto.setCompanyName(peerJson.optString("companyName"));
						peerDto.setPriceToBookValueRatio(peerJson.optDouble("priceToBookValueRatio"));
						peerDto.setPriceToEarningsValueRatio(peerJson.optDouble("priceToEarningsValueRatio"));
						peerDto.setMarketCap(peerJson.optDouble("marketCap"));
						peerDto.setPrice(peerJson.optDouble("price"));
						peerDto.setPercentChange(peerJson.optDouble("percentChange"));
						peerDto.setNetChange(peerJson.optDouble("netChange"));

						peerDto.setReturnOnAverageEquity5YearAverage(
								peerJson.optDouble("returnOnAverageEquity5YearAverage"));

						peerDto.setReturnOnAverageEquityTrailing12Month(
								peerJson.optDouble("returnOnAverageEquityTrailing12Month"));

						peerDto.setLtDebtPerEquityMostRecentFiscalYear(
								peerJson.optDouble("ltDebtPerEquityMostRecentFiscalYear"));

						peerDto.setNetProfitMargin5YearAverage(peerJson.optDouble("netProfitMargin5YearAverage"));

						peerDto.setNetProfitMarginPercentTrailing12Month(
								peerJson.optDouble("netProfitMarginPercentTrailing12Month"));

						peerDto.setDividendYieldIndicatedAnnualDividend(
								peerJson.optDouble("dividendYieldIndicatedAnnualDividend"));

						peerDto.setTotalSharesOutstanding(peerJson.optDouble("totalSharesOutstanding"));

						peerDto.setLanguageSupport(peerJson.optString("languageSupport"));
						peerDto.setImageUrl(peerJson.optString("imageUrl"));
						peerDto.setOverallRating(peerJson.optString("overallRating"));
						peerDto.setYhigh(peerJson.optDouble("yhigh"));
						peerDto.setYlow(peerJson.optDouble("ylow"));

						return peerDto;
					}).collect(Collectors.toList());
		}

		companyProfileDto.setPeerCompanyList(peerCompanyDtos);

		return companyProfileDto;
	}

	private CurrentPriceDto formCurrentPrice(String data) {
		JSONObject json = new JSONObject(data);
		JSONObject currentPriceJson = json.getJSONObject("currentPrice");
		CurrentPriceDto currentPriceDto = new CurrentPriceDto();
		currentPriceDto.setBse(currentPriceJson.optString("BSE"));
		currentPriceDto.setNse(currentPriceJson.optString("NSE"));
		return currentPriceDto;
	}

	private List<StockTechnicalDataDto> formStockTechnicalData(String data) {
		JSONObject json = new JSONObject(data);
		JSONArray stockTechnicalArray = json.getJSONArray("stockTechnicalData");
		return IntStream.range(0, stockTechnicalArray.length()).mapToObj(stockTechnicalArray::getJSONObject)
				.map(stockJson -> {
					StockTechnicalDataDto dto = new StockTechnicalDataDto();
					dto.setDays(stockJson.optInt("days"));
					dto.setBsePrice(stockJson.optDouble("bsePrice"));
					dto.setNsePrice(stockJson.optDouble("nsePrice"));
					return dto;
				}).collect(Collectors.toList());
	}

	private FinancialsDto formFinancials(String data) {

		JSONObject json = new JSONObject(data);

		JSONArray financialsArray = json.getJSONArray("financials");

		List<FinancialStatementDto> financialStatementDtos =
				IntStream.range(0, financialsArray.length())
				.mapToObj(financialsArray::getJSONObject)
				.map(finJson -> {

					FinancialStatementDto statementDto =
							new FinancialStatementDto();

					JSONObject stockFinancialMapJson =
							finJson.getJSONObject("stockFinancialMap");

					List<FinancialCategoryDto> categoryDtos =
							new ArrayList<>();

					Iterator<String> keys = stockFinancialMapJson.keys();

					while (keys.hasNext()) {

						String categoryKey = keys.next();

						Object value = stockFinancialMapJson.opt(categoryKey);

						if (!(value instanceof JSONArray)) {
							continue;
						}

						JSONArray arr = (JSONArray) value;

						List<FinancialEntryDto> entryDtos =
								IntStream.range(0, arr.length())
								.mapToObj(arr::getJSONObject)
								.map(entryJson -> {

									FinancialEntryDto dto =
											new FinancialEntryDto();

									dto.setDisplayName(
											entryJson.optString("displayName"));

									dto.setKey(
											entryJson.optString("key"));

									dto.setValue(
											entryJson.optString("value"));

									dto.setQoQComp(
											entryJson.optString("qoQComp"));

									dto.setYqoQComp(
											entryJson.optString("yqoQComp"));

									return dto;

								}).collect(Collectors.toList());

						FinancialCategoryDto categoryDto =
								new FinancialCategoryDto();

						categoryDto.setCategory(categoryKey);

						categoryDto.setEntries(entryDtos);

						categoryDtos.add(categoryDto);
					}

					statementDto.setStockFinancials(categoryDtos);

					statementDto.setFiscalYear(
							finJson.optString("FiscalYear"));

					statementDto.setEndDate(
							finJson.optString("EndDate"));

					statementDto.setType(
							finJson.optString("Type"));

					statementDto.setStatementDate(
							finJson.optString("StatementDate"));

					statementDto.setFiscalPeriodNumber(
							finJson.optInt("fiscalPeriodNumber"));

					return statementDto;

				}).collect(Collectors.toList());

		FinancialsDto financialsDto = new FinancialsDto();

		financialsDto.setFinancials(financialStatementDtos);

		return financialsDto;
	}	

	private KeyMetricsDto formKeyMetrics(String data) {
		JSONObject json = new JSONObject(data);
		JSONObject keyMetricsJson = json.getJSONObject("keyMetrics");
		KeyMetricsDto dto = new KeyMetricsDto();
		dto.setMgmtEffectiveness(mapMetrics(keyMetricsJson.getJSONArray("mgmtEffectiveness")));
		dto.setMargins(mapMetrics(keyMetricsJson.getJSONArray("margins")));
		dto.setFinancialStrength(mapMetrics(keyMetricsJson.getJSONArray("financialstrength")));
		dto.setValuation(mapMetrics(keyMetricsJson.getJSONArray("valuation")));
		dto.setIncomeStatement(mapMetrics(keyMetricsJson.getJSONArray("incomeStatement")));
		dto.setGrowth(mapMetrics(keyMetricsJson.getJSONArray("growth")));
		dto.setPerShareData(mapMetrics(keyMetricsJson.getJSONArray("persharedata")));
		dto.setPriceAndVolume(mapMetrics(keyMetricsJson.getJSONArray("priceandVolume")));
		return dto;
	}

	private List<MetricDto> mapMetrics(JSONArray array) {
		return IntStream.range(0, array.length()).mapToObj(array::getJSONObject).map(obj -> {
			MetricDto dto = new MetricDto();
			dto.setDisplayName(obj.optString("displayName"));
			dto.setKey(obj.optString("key"));
			dto.setValue(obj.optString("value"));
			return dto;
		}).collect(Collectors.toList());
	}

	private List<AnalystViewDto> formAnalystView(String data) {
		JSONObject json = new JSONObject(data);
		JSONArray analystArray = json.getJSONArray("analystView");

		return IntStream.range(0, analystArray.length()).mapToObj(analystArray::getJSONObject).map(obj -> {
			AnalystViewDto dto = new AnalystViewDto();
			dto.setColorCode(obj.optString("colorCode"));
			dto.setRatingName(obj.optString("ratingName"));
			dto.setRatingValue(obj.optInt("ratingValue"));
			dto.setNumberOfAnalystsLatest(obj.optString("numberOfAnalystsLatest"));
			dto.setNumberOfAnalysts1WeekAgo(obj.optString("numberOfAnalysts1WeekAgo"));
			dto.setNumberOfAnalysts1MonthAgo(obj.optString("numberOfAnalysts1MonthAgo"));
			dto.setNumberOfAnalysts2MonthAgo(obj.optString("numberOfAnalysts2MonthAgo"));
			dto.setNumberOfAnalysts3MonthAgo(obj.optString("numberOfAnalysts3MonthAgo"));
			return dto;
		}).collect(Collectors.toList());
	}

	private RecosBarDto formRecosBar(String data) {
		JSONObject json = new JSONObject(data);
		JSONObject recosBarJson = json.getJSONObject("recosBar");

		RecosBarDto dto = new RecosBarDto();

		// stockAnalyst array
		JSONArray analystArray = recosBarJson.getJSONArray("stockAnalyst");

		List<StockAnalystDto> stockAnalystList = IntStream.range(0, analystArray.length())
				.mapToObj(analystArray::getJSONObject).map(obj -> {
					StockAnalystDto analystDto = new StockAnalystDto();
					analystDto.setColorCode(obj.optString("colorCode"));
					analystDto.setRatingName(obj.optString("ratingName"));
					analystDto.setRatingValue(obj.optInt("ratingValue"));
					analystDto.setMinValue(obj.optDouble("minValue"));
					analystDto.setMaxValue(obj.optDouble("maxValue"));
					analystDto.setNumberOfAnalysts(obj.optInt("numberOfAnalysts"));
					return analystDto;
				}).collect(Collectors.toList());

		dto.setStockAnalyst(stockAnalystList);

		// other fields
		dto.setTickerRatingValue(recosBarJson.optInt("tickerRatingValue"));
		dto.setDataPresent(recosBarJson.optBoolean("isDataPresent"));
		dto.setNoOfRecommendations(recosBarJson.optInt("noOfRecommendations"));
		dto.setMeanValue(recosBarJson.optDouble("meanValue"));
		dto.setTickerPercentage(recosBarJson.optDouble("tickerPercentage"));

		return dto;
	}

	private RiskMeterDto formRiskMeter(String data) {
		JSONObject json = new JSONObject(data);
		JSONObject riskMeterJson = json.getJSONObject("riskMeter");

		RiskMeterDto dto = new RiskMeterDto();

		dto.setCategoryName(riskMeterJson.optString("categoryName"));
		dto.setStdDev(riskMeterJson.optDouble("stdDev"));

		return dto;
	}

	private List<ShareholdingDto> formShareholding(String data) {
		JSONObject json = new JSONObject(data);
		JSONArray shareholdingArray = json.getJSONArray("shareholding");

		return IntStream.range(0, shareholdingArray.length()).mapToObj(shareholdingArray::getJSONObject).map(obj -> {
			ShareholdingDto dto = new ShareholdingDto();

			dto.setCategoryName(obj.optString("categoryName"));
			dto.setDisplayName(obj.optString("displayName"));

			JSONArray categoriesArray = obj.optJSONArray("categories");

			List<ShareholdingCategoryDto> categoryList = IntStream.range(0, categoriesArray.length())
					.mapToObj(categoriesArray::getJSONObject).map(catObj -> {
						ShareholdingCategoryDto catDto = new ShareholdingCategoryDto();
						catDto.setHoldingDate(catObj.optString("holdingDate"));
						catDto.setPercentage(catObj.optString("percentage"));
						return catDto;
					}).collect(Collectors.toList());

			dto.setCategories(categoryList);

			return dto;
		}).collect(Collectors.toList());
	}

	private StockCorporateActionDataDto formStockCorporateActionData(String data) {
		JSONObject json = new JSONObject(data);
		JSONObject corpJson = json.getJSONObject("stockCorporateActionData");
		StockCorporateActionDataDto dto = new StockCorporateActionDataDto();
		dto.setBonus(Collections.emptyList());
		JSONArray dividendArray = corpJson.getJSONArray("dividend");
		List<DividendDto> dividendList = IntStream.range(0, dividendArray.length())
				.mapToObj(dividendArray::getJSONObject).map(obj -> {
					DividendDto d = new DividendDto();
					
					d.setCompanyName(obj.optString("companyName"));
					d.setRemarks(obj.optString("remarks"));
					d.setRecordDate(obj.optString("recordDate"));
					d.setXdDate(obj.optString("xdDate"));
					d.setInterimOrFinal(obj.optString("interimOrFinal"));
					d.setInstrumentType(obj.optInt("instrumentType"));
					d.setValue(obj.optDouble("value"));
					d.setPercentage(obj.optDouble("percentage"));
					d.setDateOfAnnouncement(obj.optString("dateOfAnnouncement"));
					d.setBookClosureStartDate(obj.optString("bookClosureStartDate"));
					d.setBookClosureEndDate(obj.optString("bookClosureEndDate"));
					d.setSortDate(obj.optString("sortDate"));
					return d;
				}).collect(Collectors.toList());
		dto.setDividend(dividendList);
		dto.setRights(Collections.emptyList());
		JSONArray splitArray = corpJson.getJSONArray("splits");
		List<SplitDto> splitList = IntStream.range(0, splitArray.length()).mapToObj(splitArray::getJSONObject)
				.map(obj -> {
					SplitDto s = new SplitDto();
					
					s.setCompanyName(obj.optString("companyName"));
					s.setRemarks(obj.optString("remarks"));
					s.setRecordDate(obj.optString("recordDate"));
					s.setXsDate(obj.optString("xsDate"));
					s.setOldFaceValue(obj.optDouble("oldFaceValue"));
					s.setNewFaceValue(obj.optDouble("newFaceValue"));
					s.setSortDate(obj.optString("sortDate"));
					return s;
				}).collect(Collectors.toList());
		dto.setSplits(splitList);

		JSONArray agmArray = corpJson.getJSONArray("annualGeneralMeeting");
		List<AnnualGeneralMeetingDto> agmList = IntStream.range(0, agmArray.length()).mapToObj(agmArray::getJSONObject)
				.map(obj -> {
					AnnualGeneralMeetingDto a = new AnnualGeneralMeetingDto();
					a.setCompanyName(obj.optString("companyName"));
					a.setRemarks(obj.optString("remarks"));
					a.setDateOfAnnouncement(obj.optString("dateOfAnnouncement"));
					a.setRecordDate(obj.optString("recordDate"));
					a.setAgmDate(obj.optString("agmDate"));
					a.setPurpose(obj.optString("purpose"));
					return a;
				}).collect(Collectors.toList());
		dto.setAnnualGeneralMeeting(agmList);

		JSONArray boardArray = corpJson.getJSONArray("boardMeetings");
		List<BoardMeetingDto> boardList = IntStream.range(0, boardArray.length()).mapToObj(boardArray::getJSONObject)
				.map(obj -> {
					BoardMeetingDto b = new BoardMeetingDto();
					
					b.setCompanyName(obj.optString("companyName"));
					b.setRemarks(obj.optString("remarks"));
					b.setBoardMeetDate(obj.optString("boardMeetDate"));
					b.setPurpose(obj.optString("purpose"));
					return b;
				}).collect(Collectors.toList());
		dto.setBoardMeetings(boardList);

		return dto;
	}

	private StockDetailsReusableDataDto formStockDetailsReusableData(String data) {
		JSONObject json = new JSONObject(data);
		JSONObject obj = json.getJSONObject("stockDetailsReusableData");

		StockDetailsReusableDataDto dto = new StockDetailsReusableDataDto();

		dto.setClose(obj.optString("close"));
		dto.setDate(obj.optString("date"));
		dto.setTime(obj.optString("time"));
		dto.setPrice(obj.optString("price"));
		dto.setPercentChange(obj.optString("percentChange"));
		dto.setMarketCap(obj.optString("marketCap"));
		dto.setYhigh(obj.optString("yhigh"));
		dto.setYlow(obj.optString("ylow"));
		dto.setHigh(obj.optString("high"));
		dto.setLow(obj.optString("low"));

		dto.setpPerEBasicExcludingExtraordinaryItemsTTM(obj.optString("pPerEBasicExcludingExtraordinaryItemsTTM"));
		dto.setCurrentDividendYieldCommonStockPrimaryIssueLTM(
				obj.optString("currentDividendYieldCommonStockPrimaryIssueLTM"));
		dto.setTotalDebtPerTotalEquityMostRecentQuarter(obj.optString("totalDebtPerTotalEquityMostRecentQuarter"));

		dto.setPriceYTDPricePercentChange(obj.optString("priceYTDPricePercentChange"));
		dto.setPrice5DayPercentChange(obj.optString("price5DayPercentChange"));

		dto.setNetIncome(obj.optString("NetIncome"));
		dto.setFiscalYear(obj.optString("FiscalYear"));
		dto.setInterimNetIncome(obj.optString("interimNetIncome"));

		dto.setSectorPriceToEarningsValueRatio(obj.optString("sectorPriceToEarningsValueRatio"));
		dto.setAverageRating(obj.optString("averageRating"));

		dto.setPromoterShareHolding(obj.optString("promoterShareHolding", null));

		JSONObject mfObj = obj.optJSONObject("mutualFundShareHolding");
		if (mfObj != null) {
			MutualFundShareHoldingDto mf = new MutualFundShareHoldingDto();
			mf.setHoldingDate(mfObj.optString("holdingDate"));
			mf.setPercentage(mfObj.optString("percentage"));
			dto.setMutualFundShareHolding(mf);
		}

		return dto;
	}

	private StockFinancialDataDto formStockFinancialData(String data) {
		JSONObject json = new JSONObject(data);
		JSONArray stockFinancialArray = json.getJSONArray("stockFinancialData");

		StockFinancialDataDto stockFinancialDataDto = new StockFinancialDataDto();
		List<StockFinancialYearDto> financialYearList = new ArrayList<>();
		for (int i = 0; i < stockFinancialArray.length(); i++) {
			JSONObject yearObj = stockFinancialArray.getJSONObject(i);
			StockFinancialYearDto yearDto = new StockFinancialYearDto();
			yearDto.setFiscalYear(yearObj.optString("FiscalYear"));
			yearDto.setEndDate(yearObj.optString("EndDate"));
			yearDto.setType(yearObj.optString("Type"));
			yearDto.setStatementDate(yearObj.optString("StatementDate"));
			yearDto.setFiscalPeriodNumber(yearObj.optInt("fiscalPeriodNumber"));
			JSONObject stockFinancialMapObj = yearObj.getJSONObject("stockFinancialMap");
			StockFinancialMapDto mapDto = new StockFinancialMapDto();
			JSONArray casArray = stockFinancialMapObj.optJSONArray("CAS");
			List<FinancialItemDto> casList = new ArrayList<>();
			if (casArray != null) {
				for (int j = 0; j < casArray.length(); j++) {
					JSONObject casObj = casArray.getJSONObject(j);
					FinancialItemDto dto = new FinancialItemDto();
					dto.setDisplayName(casObj.optString("displayName"));
					dto.setKey(casObj.optString("key"));
					dto.setValue(casObj.optString("value"));
					dto.setQoQComp(casObj.optString("qoQComp"));
					dto.setYqoQComp(casObj.optString("yqoQComp"));
					casList.add(dto);
				}
			}
			JSONArray balArray = stockFinancialMapObj.optJSONArray("BAL");
			List<FinancialItemDto> balList = new ArrayList<>();
			if (balArray != null) {
				for (int j = 0; j < balArray.length(); j++) {
					JSONObject balObj = balArray.getJSONObject(j);
					FinancialItemDto dto = new FinancialItemDto();
					dto.setDisplayName(balObj.optString("displayName"));
					dto.setKey(balObj.optString("key"));
					dto.setValue(balObj.optString("value"));
					dto.setQoQComp(balObj.optString("qoQComp"));
					dto.setYqoQComp(balObj.optString("yqoQComp"));
					balList.add(dto);
				}
			}
			JSONArray incArray = stockFinancialMapObj.optJSONArray("INC");
			List<FinancialItemDto> incList = new ArrayList<>();
			if (incArray != null) {
				for (int j = 0; j < incArray.length(); j++) {
					JSONObject incObj = incArray.getJSONObject(j);
					FinancialItemDto dto = new FinancialItemDto();
					dto.setDisplayName(incObj.optString("displayName"));
					dto.setKey(incObj.optString("key"));
					dto.setValue(incObj.optString("value"));
					dto.setQoQComp(incObj.optString("qoQComp"));
					dto.setYqoQComp(incObj.optString("yqoQComp"));

					incList.add(dto);
				}
			}
			mapDto.setCAS(casList);
			mapDto.setBAL(balList);
			mapDto.setINC(incList);
			yearDto.setStockFinancialMap(mapDto);
			financialYearList.add(yearDto);
		}
		stockFinancialDataDto.setStockFinancialData(financialYearList);
		return stockFinancialDataDto;
	}

	private RecentNewsResponseDto formRecentNews(String data) {
		JSONObject json = new JSONObject(data);
		JSONArray newsArray = json.optJSONArray("recentNews");

		RecentNewsResponseDto responseDto = new RecentNewsResponseDto();
		List<RecentNewsDto> newsList = new ArrayList<>();

		if (newsArray != null) {
			for (int i = 0; i < newsArray.length(); i++) {
				JSONObject obj = newsArray.getJSONObject(i);

				RecentNewsDto dto = new RecentNewsDto();

				dto.setId(obj.optLong("id"));
				dto.setHeadline(obj.optString("headline"));
				dto.setDate(obj.optString("date"));
				dto.setTimeToRead(obj.optInt("timeToRead"));
				dto.setUrl(obj.optString("url"));
				dto.setListimage(obj.optString("listimage"));
				dto.setThumbnailImage(obj.optString("thumbnailImage"));
				dto.setVideoBody(obj.optString("videoBody", null));
				dto.setSummary(obj.optString("summary"));
				dto.setLastPublishedDate(obj.optString("lastPublishedDate"));

				JSONObject leadMediaObj = obj.optJSONObject("leadMedia");

				if (leadMediaObj != null) {
					LeadMediaDto leadMediaDto = new LeadMediaDto();

					JSONObject imageObj = leadMediaObj.optJSONObject("image");

					if (imageObj != null) {
						ImageWrapperDto imageWrapperDto = new ImageWrapperDto();

						JSONObject imagesObj = imageObj.optJSONObject("images");

						if (imagesObj != null) {
							NewsImageDto newsImageDto = new NewsImageDto();

							newsImageDto.setThumbnailImage(imagesObj.optString("thumbnailImage"));
							newsImageDto.setBigImage(imagesObj.optString("bigImage"));

							imageWrapperDto.setImages(newsImageDto);
						}

						leadMediaDto.setImage(imageWrapperDto);
					}

					dto.setLeadMedia(leadMediaDto);
				}

				JSONObject metadataObj = obj.optJSONObject("metadata");
				if (metadataObj != null) {
					MetadataDto metadataDto = new MetadataDto();
					metadataDto.setUrl(metadataObj.optString("url"));
					dto.setMetadata(metadataDto);
				}
				newsList.add(dto);
			}
		}
		responseDto.setRecentNews(newsList);
		return responseDto;
	}

	public DataSetDto formDataSet(String data,String period) {
		if (data == null || data.isEmpty()) {
			throw new RuntimeException("empty response");
		}
		DataSetDto dataSetDto = new DataSetDto();
		dataSetDto.setPrice(formPriceResponse(data));
		dataSetDto.setVolumeData(formVolumeResponse(data));
		dataSetDto.setDma200(formDma200Response(data));
		dataSetDto.setDma50(formDma50Response(data));
		dataSetDto.setPeriod(period);
		return dataSetDto;
	}

	public PriceDto formPriceResponse(String data) {
		PriceDto priceDto = new PriceDto();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<ValueDto> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("Price")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							ValueDto dto = new ValueDto();
							dto.setDate(value.getString(0));
							dto.setValue(value.getDouble(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		priceDto.setLabel("Price");
		priceDto.setMetric("Price on NSE");
		priceDto.setValues(valueDtos);
		return priceDto;
	}

	public DMA50Dto formDma50Response(String data) {
		DMA50Dto priceDto = new DMA50Dto();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<ValueDto> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("DMA50")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							ValueDto dto = new ValueDto();
							dto.setDate(value.getString(0));
							dto.setValue(value.getDouble(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		priceDto.setLabel("DMA50");
		priceDto.setMetric("DMA50");
		priceDto.setValues(valueDtos);
		return priceDto;
	}

	public DMA200Dto formDma200Response(String data) {
		DMA200Dto priceDto = new DMA200Dto();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<ValueDto> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("DMA200")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							ValueDto dto = new ValueDto();
							dto.setDate(value.getString(0));
							dto.setValue(value.getDouble(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		priceDto.setLabel("DMA200");
		priceDto.setMetric("DMA200");
		priceDto.setValues(valueDtos);
		return priceDto;
	}

	public VolumeData formVolumeResponse(String data) {
		VolumeData volumeDto = new VolumeData();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<VolumeEntryDTO> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("Volume")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							VolumeEntryDTO dto = new VolumeEntryDTO();
							dto.setDate(value.getString(0));
							dto.setVolume(value.getLong(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		volumeDto.setLabel("Volume");
		volumeDto.setMetric("Volume on NSE");
		volumeDto.setValues(valueDtos);
		return volumeDto;
	}
}
