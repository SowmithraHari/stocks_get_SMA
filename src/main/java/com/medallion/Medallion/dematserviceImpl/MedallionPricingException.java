package com.medallion.Medallion.dematserviceImpl;

public class MedallionPricingException extends RuntimeException {

	public MedallionPricingException(String message) {
		super(message);
	}

	public MedallionPricingException(String message, Throwable cause) {
		super(message, cause);
	}
}
