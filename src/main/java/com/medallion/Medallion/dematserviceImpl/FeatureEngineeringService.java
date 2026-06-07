package com.medallion.Medallion.dematserviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medallion.Medallion.dematservice.FeatureEngService;
import com.medallion.Medallion.dto.MLFeatures;
import com.medallion.Medallion.dto.SingleDay;

@Service
public class FeatureEngineeringService implements FeatureEngService {

	@Autowired
	private MedallionAlgorithms medallionAlgorithms;

	@Override
	public MLFeatures buildFeature(List<Double> prices, SingleDay singleDay, double currentPrice, double avgVolume,
			double currentVolume) {
		MLFeatures features = new MLFeatures();
		double volatility = medallionAlgorithms.calculateVolatility(prices);
		double avgPrice = prices.stream().mapToDouble(Double::doubleValue).average().orElse(currentPrice);
		double momentum = (currentPrice - avgPrice) / avgPrice;
		double volumeFactor = currentVolume / avgVolume;
		features.setBullishProbability(singleDay.getBullishProbability());
		features.setBearishProbability(singleDay.getBearishProbability());
		features.setConfidenceScore(singleDay.getConfidenceScore());
		features.setSpread(singleDay.getCorridorSpread());
		features.setConsistency(singleDay.getSimulationConsistency());
		features.setMedianPrice(singleDay.getMedianPrice());
		features.setVolatility(volatility);
		features.setMomentum(momentum);
		features.setVolumeFactor(volumeFactor);
		return features;
	}

}
