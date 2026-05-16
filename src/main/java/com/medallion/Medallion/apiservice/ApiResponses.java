package com.medallion.Medallion.apiservice;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.medallion.Medallion.dto.DMA200Dto;
import com.medallion.Medallion.dto.DMA50Dto;
import com.medallion.Medallion.dto.DataSetDto;
import com.medallion.Medallion.dto.PriceDto;
import com.medallion.Medallion.dto.ValueDto;
import com.medallion.Medallion.dto.VolumeData;
import com.medallion.Medallion.dto.VolumeEntryDTO;

@Component
public class ApiResponses {

	public DataSetDto formDataSet(String data) {
		if (data == null || data.isEmpty()) {
			throw new RuntimeException("empty response");
		}
		DataSetDto dataSetDto = new DataSetDto();
		dataSetDto.setPrice(formPriceResponse(data));
		dataSetDto.setVolumeData(formVolumeResponse(data));
		dataSetDto.setDma200(formDma200Response(data));
		dataSetDto.setDma50(formDma50Response(data));
		return dataSetDto;
	}

	public PriceDto formPriceResponse(String data) {
		PriceDto priceDto = new PriceDto();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<ValueDto> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("Price")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							ValueDto dto = new ValueDto();
							dto.setDate(value.getString(0));
							dto.setValue(value.getDouble(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		priceDto.setLabel("Price");
		priceDto.setMetric("Price on NSE");
		priceDto.setValues(valueDtos);
		return priceDto;
	}

	public DMA50Dto formDma50Response(String data) {
		DMA50Dto priceDto = new DMA50Dto();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<ValueDto> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("DMA50")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							ValueDto dto = new ValueDto();
							dto.setDate(value.getString(0));
							dto.setValue(value.getDouble(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		priceDto.setLabel("DMA50");
		priceDto.setMetric("DMA50");
		priceDto.setValues(valueDtos);
		return priceDto;
	}

	public DMA200Dto formDma200Response(String data) {
		DMA200Dto priceDto = new DMA200Dto();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<ValueDto> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("DMA200")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							ValueDto dto = new ValueDto();
							dto.setDate(value.getString(0));
							dto.setValue(value.getDouble(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		priceDto.setLabel("DMA200");
		priceDto.setMetric("DMA200");
		priceDto.setValues(valueDtos);
		return priceDto;
	}

	public VolumeData formVolumeResponse(String data) {
		VolumeData volumeDto = new VolumeData();
		JSONObject json = new JSONObject(data);
		JSONArray datasets = json.getJSONArray("datasets");
		List<VolumeEntryDTO> valueDtos = IntStream.range(0, datasets.length()).mapToObj(datasets::getJSONObject)
				.flatMap(dataset -> {
					String metrics = dataset.get("metric").toString();
					if (metrics.equalsIgnoreCase("Volume")) {
						JSONArray values = dataset.getJSONArray("values");
						return IntStream.range(0, values.length()).mapToObj(values::getJSONArray).map(value -> {
							VolumeEntryDTO dto = new VolumeEntryDTO();
							dto.setDate(value.getString(0));
							dto.setVolume(value.getLong(1));
							return dto;
						});
					}
					return null;
				}).filter(Objects::nonNull).toList();
		volumeDto.setLabel("Volume");
		volumeDto.setMetric("Volume on NSE");
		volumeDto.setValues(valueDtos);
		return volumeDto;
	}
}
