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
@Table(name = "shareholding")
public class ShareholdingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "display_name")
	private String displayName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "shareholding_id")
	private List<ShareholdingCategoryEntity> categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<ShareholdingCategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(List<ShareholdingCategoryEntity> categories) {
		this.categories = categories;
	}
}