package com.medallion.Medallion.dto;

public class DividendDto {

	
	private String companyName;
	private String remarks;
	private String recordDate;
	private String xdDate;
	private String interimOrFinal;
	private int instrumentType;
	private double value;
	private double percentage;
	private String dateOfAnnouncement;
	private String bookClosureStartDate;
	private String bookClosureEndDate;
	private String sortDate;

	

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
