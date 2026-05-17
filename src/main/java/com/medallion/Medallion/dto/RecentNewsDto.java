package com.medallion.Medallion.dto;

public class RecentNewsDto {

    private long id;
    private String headline;
    private String date;
    private Integer timeToRead;
    private String url;
    private String listimage;
    private String thumbnailImage;
    private String videoBody;
    private String summary;
    private String lastPublishedDate;

    private LeadMediaDto leadMedia;
    private MetadataDto metadata;

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

    public LeadMediaDto getLeadMedia() {
        return leadMedia;
    }

    public void setLeadMedia(LeadMediaDto leadMedia) {
        this.leadMedia = leadMedia;
    }

    public MetadataDto getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataDto metadata) {
        this.metadata = metadata;
    }
}
