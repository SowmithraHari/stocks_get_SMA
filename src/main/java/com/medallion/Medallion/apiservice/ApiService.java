package com.medallion.Medallion.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.medallion.Medallion.dto.DataSetDto;
import com.medallion.Medallion.dto.StockDto;

@Component
public class ApiService {

	@Value("${indian.api}")
	private String apiKey;

	@Value("${indian.url}")
	private String apiurl;

	private final RestClient restClient;

	public ApiService() {
		this.restClient = RestClient.builder().baseUrl(apiurl).defaultHeader("x-api-key", apiKey).build();
	}

	@Autowired
	private ApiResponses apiResponses;

	public StockDto getStockData(String stockName) {
		RestTemplate restTemplate = new RestTemplate();
		String url = apiurl + "/stock?name=" + stockName;
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", apiKey);
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String data = response.getBody();
		return apiResponses.formStockDto(data);
	}

	public String getTrendingStocks() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", apiKey);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(apiurl, HttpMethod.GET, entity, String.class);
		return response.getBody();
	}

	public DataSetDto getHistoricalData(String stockName, String period, String filter) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", apiKey);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		String url = apiurl + "/historical_data" + "?stock_name=" + stockName + "&period=" + period + "&filter="
				+ filter;
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String data = response.getBody();
		return apiResponses.formDataSet(data,period);
	}

	public String getIpoData() {
		return restClient.get().uri("/ipo").retrieve().body(String.class);
	}

	public String getHistoricalStats(String stockName, String stats) {
		return restClient.get().uri(uriBuilder -> uriBuilder.path("/historical_stats")
				.queryParam("stock_name", stockName).queryParam("stats", stats).build()).retrieve().body(String.class);
	}

	public String getStockForecasts(String stockId, String measureCode, String periodType, String dataType,
			String age) {
		return restClient.get()
				.uri(uriBuilder -> uriBuilder.path("/stock_forecasts").queryParam("stock_id", stockId)
						.queryParam("measure_code", measureCode).queryParam("period_type", periodType)
						.queryParam("data_type", dataType).queryParam("age", age).build())
				.retrieve().body(String.class);
	}

	public String searchIndustry(String query) {
		return restClient.get()
				.uri(uriBuilder -> uriBuilder.path("/industry_search").queryParam("query", query).build()).retrieve()
				.body(String.class);
	}

	public String getCorporateActions(String stockName) {
		return restClient.get()
				.uri(uriBuilder -> uriBuilder.path("/corporate_actions").queryParam("stock_name", stockName).build())
				.retrieve().body(String.class);
	}

	public String getNews() {
		return restClient.get().uri("/news").retrieve().body(String.class);
	}

	public String getStockTargetPrice(String stockId) {
		return restClient.get()
				.uri(uriBuilder -> uriBuilder.path("/stock_target_price").queryParam("stock_id", stockId).build())
				.retrieve().body(String.class);
	}

	public String getRecentAnnouncements(String stockName) {
		return restClient.get()
				.uri(uriBuilder -> uriBuilder.path("/recent_announcements").queryParam("stock_name", stockName).build())
				.retrieve().body(String.class);
	}

	public String fetch52WeekHighLowData() {
		return restClient.get().uri("/fetch_52_week_high_low_data").retrieve().body(String.class);
	}

	public String getNseMostActive() {
		return restClient.get().uri("/NSE_most_active").retrieve().body(String.class);
	}

	public String getPriceShockers() {
		return restClient.get().uri("/price_shockers").retrieve().body(String.class);
	}

}
