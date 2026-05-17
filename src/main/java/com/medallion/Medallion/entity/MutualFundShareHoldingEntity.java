package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mutual_fund_share_holding")
public class MutualFundShareHoldingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "holding_date")
	private String holdingDate;

	@Column(name = "percentage")
	private String percentage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHoldingDate() {
		return holdingDate;
	}

	public void setHoldingDate(String holdingDate) {
		this.holdingDate = holdingDate;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
