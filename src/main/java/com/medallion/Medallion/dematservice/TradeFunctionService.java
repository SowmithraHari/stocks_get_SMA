package com.medallion.Medallion.dematservice;

import java.util.List;

import com.medallion.Medallion.dto.DirectionalProbabilityDto;
import com.medallion.Medallion.dto.SimulationPathDto;
import com.medallion.Medallion.dto.VolumeData;

public interface TradeFunctionService {

	DirectionalProbabilityDto calculateDirectionalProbability(List<Double> prices, VolumeData volumeData,
			double currentPrice, int simulations);

	List<SimulationPathDto> generateMonteCarloPaths(List<Double> prices, VolumeData volumeData, double currentPrice,
			int days, int simulations);

}
