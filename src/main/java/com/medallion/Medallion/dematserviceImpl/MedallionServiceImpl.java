package com.medallion.Medallion.dematserviceImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medallion.Medallion.apiservice.ApiService;
import com.medallion.Medallion.dematservice.MedallionService;
import com.medallion.Medallion.dematservice.TradeFunctionService;
import com.medallion.Medallion.dto.AnalystViewDto;
import com.medallion.Medallion.dto.CompanyProfileDto;
import com.medallion.Medallion.dto.CurrentPriceDto;
import com.medallion.Medallion.dto.DataSetDto;
import com.medallion.Medallion.dto.DirectionalProbabilityDto;
import com.medallion.Medallion.dto.FinancialsDto;
import com.medallion.Medallion.dto.KeyMetricsDto;
import com.medallion.Medallion.dto.OfficerDto;
import com.medallion.Medallion.dto.RecentNewsResponseDto;
import com.medallion.Medallion.dto.RecosBarDto;
import com.medallion.Medallion.dto.RiskMeterDto;
import com.medallion.Medallion.dto.ShareholdingDto;
import com.medallion.Medallion.dto.SimulationPathDto;
import com.medallion.Medallion.dto.StockCorporateActionDataDto;
import com.medallion.Medallion.dto.StockDetailsReusableDataDto;
import com.medallion.Medallion.dto.StockDto;
import com.medallion.Medallion.dto.StockFinancialDataDto;
import com.medallion.Medallion.dto.StockRequest;
import com.medallion.Medallion.dto.StockTechnicalDataDto;
import com.medallion.Medallion.dto.ValueDto;
import com.medallion.Medallion.entity.AnalystViewEntity;
import com.medallion.Medallion.entity.CompanyProfileEntity;
import com.medallion.Medallion.entity.CurrentPriceEntity;
import com.medallion.Medallion.entity.DatasetEntity;
import com.medallion.Medallion.entity.FinancialCategoryEntity;
import com.medallion.Medallion.entity.FinancialEntryEntity;
import com.medallion.Medallion.entity.FinancialStatementEntity;
import com.medallion.Medallion.entity.FinancialsEntity;
import com.medallion.Medallion.entity.KeyMetricsEntity;
import com.medallion.Medallion.entity.OfficerEntity;
import com.medallion.Medallion.entity.PeerCompanyEntity;
import com.medallion.Medallion.entity.RecentNewsResponseEntity;
import com.medallion.Medallion.entity.RecosBarEntity;
import com.medallion.Medallion.entity.RiskMeterEntity;
import com.medallion.Medallion.entity.ShareholdingEntity;
import com.medallion.Medallion.entity.StockCorporateActionDataEntity;
import com.medallion.Medallion.entity.StockDetailsReusableDataEntity;
import com.medallion.Medallion.entity.StockEntity;
import com.medallion.Medallion.entity.StockFinancialDataEntity;
import com.medallion.Medallion.entity.StockTechnicalDataEntity;
import com.medallion.Medallion.repo.DataSetRepository;
import com.medallion.Medallion.repo.StockRepository;

@Service
public class MedallionServiceImpl implements MedallionService {

	@Autowired
	private ApiService apiService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DataSetRepository dataSetRepository;

	@Autowired
	private TradeFunctionService functionService;

	@Autowired
	private StockRepository stockRepository;

	@Override
	public StockDto getStockDetails(StockRequest stockRequest) {
		StockDto stockDto = null;
		StockEntity stockentity = stockRepository.findTopByCompanyNameAndCreatedDateLessThanEqualOrderByCreatedDateDesc(
				stockRequest.getStock(), LocalDate.now());
		if (stockentity == null) {
			stockDto = apiService.getStockData(stockRequest.getStock());
			try {
				stockentity = new StockEntity();
				stockentity.setCompanyName(stockDto.getCompanyName());
				AnalystViewDto anal = stockDto.getAnalystView();
				CompanyProfileEntity companyentity = new CompanyProfileEntity();
				CompanyProfileDto companyProfile = stockDto.getCompanyProfile();
				companyentity.setCompanyDescription(companyProfile.getCompanyDescription());
				companyentity.setExchangeCodeBse(companyProfile.getExchangeCodeBse());
				companyentity.setExchangeCodeNse(companyProfile.getExchangeCodeNse());
				companyentity.setIsinId(companyProfile.getIsinId());
				companyentity.setMgIndustry(companyProfile.getMgIndustry());
				List<OfficerDto> officers = companyProfile.getOfficers();
				List<OfficerEntity> officeren = officers.stream().map(officer -> {
					return modelMapper.map(officers, OfficerEntity.class);
				}).collect(Collectors.toList());
				companyentity.setOfficers(officeren);
				List<PeerCompanyEntity> peerList = companyProfile.getPeerCompanyList().stream().map(peer -> {
					return modelMapper.map(peer, PeerCompanyEntity.class);
				}).collect(Collectors.toList());
				companyentity.setPeerCompanyList(peerList);
				stockentity.setCompanyProfile(companyentity);
				CurrentPriceDto currenntPrice = stockDto.getCurrentPrice();
				CurrentPriceEntity currentPriceEnt = modelMapper.map(currenntPrice, CurrentPriceEntity.class);
				stockentity.setCurrentPrice(currentPriceEnt);
				StockTechnicalDataDto technical = stockDto.getStockTechnicalData();
				StockTechnicalDataEntity technicalen = modelMapper.map(technical, StockTechnicalDataEntity.class);
				stockentity.setStockTechnicalData(technicalen);
				FinancialsDto financials = stockDto.getFinancials();
				FinancialsEntity financialsEntity = modelMapper.map(financials, FinancialsEntity.class);
				if (financialsEntity.getFinancials() != null) {
					for (FinancialStatementEntity stmt : financialsEntity.getFinancials()) {
						stmt.setFinancials(financialsEntity);
						if (stmt.getStockFinancials() != null) {
							for (FinancialCategoryEntity cat : stmt.getStockFinancials()) {
								cat.setFinancialStatement(stmt);
								if (cat.getEntries() != null) {
									for (FinancialEntryEntity entry : cat.getEntries()) {
										entry.setFinancialCategory(cat);
									}
								}
							}
						}
					}
				}
				stockentity.setFinancials(financialsEntity);
				KeyMetricsDto metrics = stockDto.getKeyMetrics();
				KeyMetricsEntity metricentity = modelMapper.map(metrics, KeyMetricsEntity.class);
				stockentity.setKeyMetrics(metricentity);
				String expirydate = stockDto.getFutureExpiryDates();
				stockentity.setFutureExpiryDates(expirydate);
				String overview = stockDto.getFutureOverviewData();
				stockentity.setFutureOverviewData(overview);
				String initial = stockDto.getInitialStockFinancialData();
				stockentity.setInitialStockFinancialData(initial);
				RecosBarDto resco = stockDto.getRecosBar();
				RecosBarEntity rescoen = modelMapper.map(resco, RecosBarEntity.class);
				stockentity.setRecosBar(rescoen);
				RiskMeterDto riskmeter = stockDto.getRiskMeter();
				RiskMeterEntity riskcentity = modelMapper.map(riskmeter, RiskMeterEntity.class);
				stockentity.setRiskMeter(riskcentity);
				ShareholdingDto shareholding = stockDto.getShareholding();
				ShareholdingEntity shareholdingEn = modelMapper.map(shareholding, ShareholdingEntity.class);
				stockentity.setShareholding(shareholdingEn);
				StockCorporateActionDataDto corporateActin = stockDto.getStockCorporateActionData();
				StockCorporateActionDataEntity corporateActinEn = modelMapper.map(corporateActin,
						StockCorporateActionDataEntity.class);
				stockentity.setStockCorporateActionData(corporateActinEn);
				StockDetailsReusableDataDto reusableData = stockDto.getStockDetailsReusableData();
				StockDetailsReusableDataEntity reusableDataen = modelMapper.map(reusableData,
						StockDetailsReusableDataEntity.class);
				stockentity.setStockDetailsReusableData(reusableDataen);
				StockFinancialDataDto yearfinacials = stockDto.getStockFinancialData();
				StockFinancialDataEntity yearfinacialsen = modelMapper.map(yearfinacials,
						StockFinancialDataEntity.class);
				stockentity.setStockFinancialData(yearfinacialsen);
				RecentNewsResponseDto news = stockDto.getRecentNews();
				RecentNewsResponseEntity newsEn = modelMapper.map(news, RecentNewsResponseEntity.class);
				stockentity.setRecentNews(newsEn);
				AnalystViewEntity analystEntity = modelMapper.map(anal, AnalystViewEntity.class);
				stockentity.setAnalystView(analystEntity);
				stockRepository.save(stockentity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			stockDto = modelMapper.map(stockentity, StockDto.class);
		}
		stockDto.setDataSetDto(getHistoricalPrice(stockRequest.getStock()));
		return stockDto;
	}

	@Override
	public DirectionalProbabilityDto getDirectionalProbability(StockRequest stockRequest) {
		DataSetDto dataSetDto = null;
		DatasetEntity dataset = dataSetRepository
				.findTopByStocknameAndPeriodOrderByCreatedDateDesc(stockRequest.getStock(), stockRequest.getPeriod());
		if (dataset == null) {
			dataSetDto = apiService.getHistoricalData(stockRequest.getStock(), stockRequest.getPeriod(), "default");
			DatasetEntity datasetEntity = modelMapper.map(dataSetDto, DatasetEntity.class);
			datasetEntity.setStockname(stockRequest.getStock());
			dataSetRepository.save(datasetEntity);
		} else {
			dataSetDto = modelMapper.map(dataset, DataSetDto.class);
		}
		ValueDto currentPrice = dataSetDto.getPrice().getValues().stream()
				.max(Comparator.comparing(e -> LocalDate.parse(e.getDate())))
				.orElseThrow(() -> new MedallionPricingException("No volume entries found"));

		List<Double> prices = dataSetDto.getPrice().getValues().stream().map(ValueDto::getValue)
				.collect(Collectors.toList());
		DirectionalProbabilityDto result = functionService.calculateDirectionalProbability(prices,
				dataSetDto.getVolumeData(), currentPrice.getValue(), stockRequest.getCount());
		List<SimulationPathDto> multidays = functionService.generateMonteCarloPaths(prices, dataSetDto.getVolumeData(),
				currentPrice.getValue(), stockRequest.getDays(), stockRequest.getCount());
		result.setMultidays(multidays);
		result.setDataSetDto(dataSetDto);
		return result;
	}

	@Override
	public DataSetDto getHistoricalPrice(String stockname) {
		DataSetDto dataSetDto = null;
		DatasetEntity dataset = dataSetRepository
				.findTopByStocknameAndCreatedDateLessThanEqualOrderByCreatedDateDesc(stockname, LocalDate.now());
		if (dataset == null) {
			dataSetDto = apiService.getHistoricalData(stockname, "6m", "default");
			DatasetEntity datasetEntity = modelMapper.map(dataSetDto, DatasetEntity.class);
			datasetEntity.setStockname(stockname);
			dataSetRepository.save(datasetEntity);
		} else {
			dataSetDto = modelMapper.map(dataset, DataSetDto.class);
		}
		return dataSetDto;
	}

}
