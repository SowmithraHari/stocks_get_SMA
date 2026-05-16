package com.medallion.Medallion.validation;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ValidateMedallion {
	
	public void validateInputs(List<Double> prices, double currentPrice, int simulations) {
		if (simulations <= 0) {
			throw new IllegalArgumentException("simulations must be positive");
		}
		if (prices == null || prices.isEmpty()) {
			throw new IllegalArgumentException("prices must not be null or empty");
		}
		if (currentPrice <= 0) {
			throw new IllegalArgumentException("currentPrice must be positive");
		}
	}

}
