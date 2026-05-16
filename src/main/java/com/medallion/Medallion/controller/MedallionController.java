package com.medallion.Medallion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medallion.Medallion.dematservice.MedallionService;
import com.medallion.Medallion.dto.DirectionalProbabilityDto;
import com.medallion.Medallion.dto.StockRequest;

@RestController
@RequestMapping("/medallion")
public class MedallionController {

	@Autowired
	private MedallionService medallionService;

	@PostMapping("/getMedallion")
	public ResponseEntity<DirectionalProbabilityDto> getMedallion(
	        @RequestBody StockRequest stockRequest) {

	    DirectionalProbabilityDto result =
	            medallionService.getDirectionalProbability(stockRequest);

	    return ResponseEntity.ok(result);
	}

}
