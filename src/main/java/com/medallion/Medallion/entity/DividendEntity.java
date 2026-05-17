package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dividend")
public class DividendEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "company_name")
	private String companyName;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "record_date")
	private String recordDate;

	@Column(name = "xd_date")
	private String xdDate;

	@Column(name = "interim_or_final")
	private String interimOrFinal;

	@Column(name = "instrument_type")
	private int instrumentType;

	@Column(name = "value")
	private double value;

	@Column(name = "percentage")
	private double percentage;

	@Column(name = "date_of_announcement")
	private String dateOfAnnouncement;

	@Column(name = "book_closure_start_date")
	private String bookClosureStartDate;

	@Column(name = "book_closure_end_date")
	private String bookClosureEndDate;

	@Column(name = "sort_date")
	private String sortDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getXdDate() {
		return xdDate;
	}

	public void setXdDate(String xdDate) {
		this.xdDate = xdDate;
	}

	public String getInterimOrFinal() {
		return interimOrFinal;
	}

	public void setInterimOrFinal(String interimOrFinal) {
		this.interimOrFinal = interimOrFinal;
	}

	public int getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(int instrumentType) {
		this.instrumentType = instrumentType;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getDateOfAnnouncement() {
		return dateOfAnnouncement;
	}

	public void setDateOfAnnouncement(String dateOfAnnouncement) {
		this.dateOfAnnouncement = dateOfAnnouncement;
	}

	public String getBookClosureStartDate() {
		return bookClosureStartDate;
	}

	public void setBookClosureStartDate(String bookClosureStartDate) {
		this.bookClosureStartDate = bookClosureStartDate;
	}

	public String getBookClosureEndDate() {
		return bookClosureEndDate;
	}

	public void setBookClosureEndDate(String bookClosureEndDate) {
		this.bookClosureEndDate = bookClosureEndDate;
	}

	public String getSortDate() {
		return sortDate;
	}

	public void setSortDate(String sortDate) {
		this.sortDate = sortDate;
	}
}
