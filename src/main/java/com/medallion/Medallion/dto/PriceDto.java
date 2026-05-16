package com.medallion.Medallion.dto;

import java.util.List;

public class PriceDto {

	private String metric;
	private String label;
	private List<ValueDto> values;
	
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<ValueDto> getValues() {
		return values;
	}
	public void setValues(List<ValueDto> values) {
		this.values = values;
	}
	
	

}
