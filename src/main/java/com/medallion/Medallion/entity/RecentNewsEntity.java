package com.medallion.Medallion.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recent_news")
public class RecentNewsEntity {

	@Id
	private long id;

	@Column(name = "headline", columnDefinition = "TEXT")
	private String headline;

	@Column(name = "news_date")
	private String date;

	@Column(name = "time_to_read")
	private Integer timeToRead;

	@Column(name = "url", columnDefinition = "TEXT")
	private String url;

	@Column(name = "list_image", columnDefinition = "TEXT")
	private String listimage;

	@Column(name = "thumbnail_image", columnDefinition = "TEXT")
	private String thumbnailImage;

	@Column(name = "video_body", columnDefinition = "TEXT")
	private String videoBody;

	@Column(name = "summary", columnDefinition = "TEXT")
	private String summary;

	@Column(name = "last_published_date")
	private String lastPublishedDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lead_media_id")
	private LeadMediaEntity leadMedia;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "metadata_id")
	private MetadataEntity metadata;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getTimeToRead() {
		return timeToRead;
	}

	public void setTimeToRead(Integer timeToRead) {
		this.timeToRead = timeToRead;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getListimage() {
		return listimage;
	}

	public void setListimage(String listimage) {
		this.listimage = listimage;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public String getVideoBody() {
		return videoBody;
	}

	public void setVideoBody(String videoBody) {
		this.videoBody = videoBody;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLastPublishedDate() {
		return lastPublishedDate;
	}

	public void setLastPublishedDate(String lastPublishedDate) {
		this.lastPublishedDate = lastPublishedDate;
	}

	public LeadMediaEntity getLeadMedia() {
		return leadMedia;
	}

	public void setLeadMedia(LeadMediaEntity leadMedia) {
		this.leadMedia = leadMedia;
	}

	public MetadataEntity getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataEntity metadata) {
		this.metadata = metadata;
	}
}
