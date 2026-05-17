package com.medallion.Medallion.dematservice;

import com.medallion.Medallion.dto.DirectionalProbabilityDto;
import com.medallion.Medallion.dto.StockDto;
import com.medallion.Medallion.dto.StockRequest;

public interface MedallionService {

	DirectionalProbabilityDto getDirectionalProbability(StockRequest stockRequest);

	StockDto getStockDetails(StockRequest stockRequest);

}
