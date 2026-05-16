package com.medallion.Medallion.dto;

import java.util.List;

public class VolumeData {

	private String metric;
	private String label;
	private List<VolumeEntryDTO> values;
	private double avgVolume;
    private long currVolume;
    
    
	public double getAvgVolume() {
		return avgVolume;
	}
	public void setAvgVolume(double avgVolume) {
		this.avgVolume = avgVolume;
	}
	public long getCurrVolume() {
		return currVolume;
	}
	public void setCurrVolume(long currVolume) {
		this.currVolume = currVolume;
	}
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
	public List<VolumeEntryDTO> getValues() {
		return values;
	}
	public void setValues(List<VolumeEntryDTO> values) {
		this.values = values;
	}
	
	

}
