package com.medallion.Medallion.dto;

import java.util.List;

public class ShareholdingDto {

	private String categoryName;
	private String displayName;
	private List<ShareholdingCategoryDto> categories;

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

	public List<ShareholdingCategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(List<ShareholdingCategoryDto> categories) {
		this.categories = categories;
	}

}
