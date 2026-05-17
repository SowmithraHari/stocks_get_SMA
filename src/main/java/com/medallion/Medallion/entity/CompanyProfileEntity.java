package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_profile")
public class CompanyProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_description", columnDefinition = "TEXT")
	private String companyDescription;

	@Column(name = "mg_industry")
	private String mgIndustry;

	@Column(name = "isin_id")
	private String isinId;

	@Column(name = "exchange_code_bse")
	private String exchangeCodeBse;

	@Column(name = "exchange_code_nse")
	private String exchangeCodeNse;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_profile_id")
	private List<OfficerEntity> officers;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_profile_id")
	private List<PeerCompanyEntity> peerCompanyList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<OfficerEntity> getOfficers() {
		return officers;
	}

	public void setOfficers(List<OfficerEntity> officers) {
		this.officers = officers;
	}

	public List<PeerCompanyEntity> getPeerCompanyList() {
		return peerCompanyList;
	}

	public void setPeerCompanyList(List<PeerCompanyEntity> peerCompanyList) {
		this.peerCompanyList = peerCompanyList;
	}
}

