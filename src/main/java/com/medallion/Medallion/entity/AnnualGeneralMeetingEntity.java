package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "annual_general_meeting")
public class AnnualGeneralMeetingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ticker_id")
	private String tickerId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "date_of_announcement")
	private String dateOfAnnouncement;

	@Column(name = "record_date")
	private String recordDate;

	@Column(name = "agm_date")
	private String agmDate;

	@Column(name = "purpose")
	private String purpose;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTickerId() {
		return tickerId;
	}

	public void setTickerId(String tickerId) {
		this.tickerId = tickerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDateOfAnnouncement() {
		return dateOfAnnouncement;
	}

	public void setDateOfAnnouncement(String dateOfAnnouncement) {
		this.dateOfAnnouncement = dateOfAnnouncement;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getAgmDate() {
		return agmDate;
	}

	public void setAgmDate(String agmDate) {
		this.agmDate = agmDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}
