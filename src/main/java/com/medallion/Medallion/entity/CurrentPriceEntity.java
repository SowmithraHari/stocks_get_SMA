package com.medallion.Medallion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "current_price")
public class CurrentPriceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bse")
	private String bse;

	@Column(name = "nse")
	private String nse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBse() {
		return bse;
	}

	public void setBse(String bse) {
		this.bse = bse;
	}

	public String getNse() {
		return nse;
	}

	public void setNse(String nse) {
		this.nse = nse;
	}
}
