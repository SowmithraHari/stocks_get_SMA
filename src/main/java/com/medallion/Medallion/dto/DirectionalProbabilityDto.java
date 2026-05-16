package com.medallion.Medallion.dto;

import java.util.List;

public class DirectionalProbabilityDto {

	private SingleDay oneday;

	List<SimulationPathDto> multidays;
	
	private DataSetDto dataSetDto;
	

	public SingleDay getOneday() {
		return oneday;
	}

	public void setOneday(SingleDay oneday) {
		this.oneday = oneday;
	}

	public List<SimulationPathDto> getMultidays() {
		return multidays;
	}

	public void setMultidays(List<SimulationPathDto> multidays) {
		this.multidays = multidays;
	}

	public DataSetDto getDataSetDto() {
		return dataSetDto;
	}

	public void setDataSetDto(DataSetDto dataSetDto) {
		this.dataSetDto = dataSetDto;
	}


}