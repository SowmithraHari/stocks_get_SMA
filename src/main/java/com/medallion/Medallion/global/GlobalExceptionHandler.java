package com.medallion.Medallion.global;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.medallion.Medallion.dematserviceImpl.MedallionPricingException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidationErrors(MethodArgumentNotValidException ex) {

		String errors = ex.getBindingResult().getFieldErrors().stream()
				.map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining(", "));

		log.warn("Validation failed: {}", errors);
		return ResponseEntity.badRequest().body(ApiResponse.failure(errors, "VALIDATION_ERROR"));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {

		log.warn("Illegal argument: {}", ex.getMessage());
		return ResponseEntity.badRequest().body(ApiResponse.failure(ex.getMessage(), "INVALID_INPUT"));
	}

	@ExceptionHandler(MedallionPricingException.class)
	public ResponseEntity<ApiResponse<Void>> handlePricingException(MedallionPricingException ex) {

		log.error("Pricing exception: {}", ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(ApiResponse.failure(ex.getMessage(), "SIMULATION_FAILED"));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
		log.error("Unexpected error", ex);
		return ResponseEntity.internalServerError()
				.body(ApiResponse.failure("An unexpected error occurred", "INTERNAL_ERROR"));
	}
}
