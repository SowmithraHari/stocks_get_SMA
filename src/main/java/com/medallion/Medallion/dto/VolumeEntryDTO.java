package com.medallion.Medallion.dto;

import java.util.Map;

public class VolumeEntryDTO {

	private String date;
	private long volume;
	private Map<String, Object> props;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public Map<String, Object> getProps() {
		return props;
	}
	public void setProps(Map<String, Object> props) {
		this.props = props;
	}
	
	
}
