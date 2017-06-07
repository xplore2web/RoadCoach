package com.alcord.modelmappers;

public class RatingDetail {
	
	private String id;
	private String tripId;
	private Integer stars;
	private String comment;
	private Boolean IsIssues;
	private String  ratingBy;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getIsIssues() {
		return IsIssues;
	}
	public void setIsIssues(Boolean isIssues) {
		IsIssues = isIssues;
	}
	public String getRatingBy() {
		return ratingBy;
	}
	public void setRatingBy(String ratingBy) {
		this.ratingBy = ratingBy;
	}
	
	
	
	
}
