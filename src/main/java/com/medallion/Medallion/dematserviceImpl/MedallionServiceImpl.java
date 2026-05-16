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
import com.medallion.Medallion.dto.DataSetDto;
import com.medallion.Medallion.dto.DirectionalProbabilityDto;
import com.medallion.Medallion.dto.SimulationPathDto;
import com.medallion.Medallion.dto.StockRequest;
import com.medallion.Medallion.dto.ValueDto;
import com.medallion.Medallion.entity.DatasetEntity;
import com.medallion.Medallion.repo.DataSetRepository;

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

	@Override
	public DirectionalProbabilityDto getDirectionalProbability(StockRequest stockRequest) {
		DataSetDto dataSetDto = null;
		DatasetEntity dataset = dataSetRepository
				.findTopByStocknameAndCreatedDateLessThanEqualOrderByCreatedDateDesc(stockRequest.getStock(),LocalDate.now());
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

}
