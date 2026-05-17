package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "split")
public class SplitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "record_date")
	private String recordDate;

	@Column(name = "xs_date")
	private String xsDate;

	@Column(name = "old_face_value")
	private double oldFaceValue;

	@Column(name = "new_face_value")
	private double newFaceValue;

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

	public String getXsDate() {
		return xsDate;
	}

	public void setXsDate(String xsDate) {
		this.xsDate = xsDate;
	}

	public double getOldFaceValue() {
		return oldFaceValue;
	}

	public void setOldFaceValue(double oldFaceValue) {
		this.oldFaceValue = oldFaceValue;
	}

	public double getNewFaceValue() {
		return newFaceValue;
	}

	public void setNewFaceValue(double newFaceValue) {
		this.newFaceValue = newFaceValue;
	}

	public String getSortDate() {
		return sortDate;
	}

	public void setSortDate(String sortDate) {
		this.sortDate = sortDate;
	}
}