package com.medallion.Medallion.dto;

import java.util.List;

public class CompanyProfileDto {

	private String companyDescription;
	private String mgIndustry;
	private String isinId;
	private String exchangeCodeBse;
	private String exchangeCodeNse;

	private List<OfficerDto> officers;
	private List<PeerCompanyDto> peerCompanyList;
	
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public String getMgIndustry() {
		return mgIndustry;
	}
	public void setMgIndustry(String mgIndustry) {
		this.mgIndustry = mgIndustry;
	}
	public String getIsinId() {
		return isinId;
	}
	public void setIsinId(String isinId) {
		this.isinId = isinId;
	}
	public String getExchangeCodeBse() {
		return exchangeCodeBse;
	}
	public void setExchangeCodeBse(String exchangeCodeBse) {
		this.exchangeCodeBse = exchangeCodeBse;
	}
	public String getExchangeCodeNse() {
		return exchangeCodeNse;
	}
	public void setExchangeCodeNse(String exchangeCodeNse) {
		this.exchangeCodeNse = exchangeCodeNse;
	}
	
	public List<PeerCompanyDto> getPeerCompanyList() {
		return peerCompanyList;
	}
	public void setPeerCompanyList(List<PeerCompanyDto> peerCompanyList) {
		this.peerCompanyList = peerCompanyList;
	}
	public List<OfficerDto> getOfficers() {
		return officers;
	}
	public void setOfficers(List<OfficerDto> officers) {
		this.officers = officers;
	}
	
	
	
	
	

}
