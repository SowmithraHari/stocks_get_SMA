package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recent_news_response")
public class RecentNewsResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recent_news_response_id")
	private List<RecentNewsEntity> recentNews;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<RecentNewsEntity> getRecentNews() {
		return recentNews;
	}

	public void setRecentNews(List<RecentNewsEntity> recentNews) {
		this.recentNews = recentNews;
	}
}
