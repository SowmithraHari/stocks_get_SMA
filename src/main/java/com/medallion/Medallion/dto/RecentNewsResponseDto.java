package com.medallion.Medallion.dto;

import java.util.List;

public class RecentNewsResponseDto {

	private List<RecentNewsDto> recentNews;

	public List<RecentNewsDto> getRecentNews() {
		return recentNews;
	}

	public void setRecentNews(List<RecentNewsDto> recentNews) {
		this.recentNews = recentNews;
	}
}
