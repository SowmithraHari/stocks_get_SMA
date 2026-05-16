package com.medallion.Medallion.global;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private final boolean success;
	private final String message;
	private final T data;
	private final String timestamp;
	private final String errorCode;

	private ApiResponse(boolean success, String message, T data, String timestamp, String errorCode) {
		this.success = success;
		this.message = message;
		this.data = data;
		this.timestamp = timestamp;
		this.errorCode = errorCode;
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(true, null, data, Instant.now().toString(), null);
	}

	public static <T> ApiResponse<T> failure(String message, String errorCode) {
		return new ApiResponse<>(false, message, null, Instant.now().toString(), errorCode);
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}
}