package com.medallion.Medallion.dematservice;

import java.util.List;

import com.medallion.Medallion.dto.MLFeatures;
import com.medallion.Medallion.dto.SingleDay;

public interface FeatureEngService {

	MLFeatures buildFeature(List<Double> prices, SingleDay singleDay, double currentPrice, double avgVolume,
			double currentVolume);

}
