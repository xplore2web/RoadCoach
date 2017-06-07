/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "rating")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
    private UUID id;
    @Column(name = "stars")
    private Integer stars;
    @Size(max = 100)
    @Column(name = "rating_comment")
    private String ratingComment;
    @Size(max = 50)
    @Column(name = "rating_by")
    private String ratingBy;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "fkey_driver_id", referencedColumnName = "id")
    @ManyToOne
    private Driver fkeyDriverId;
    @JoinColumn(name = "fkey_passenger_id", referencedColumnName = "id")
    @ManyToOne
    private Passenger fkeyPassengerId;
    @JoinColumn(name = "fkey_trip_id", referencedColumnName = "id")
    @ManyToOne
    private Trip fkeyTripId;
    @Column(name = "is_issues")
    private Boolean isIssues;

    public Rating() {
    }

    
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getRatingComment() {
        return ratingComment;
    }

    public void setRatingComment(String ratingComment) {
        this.ratingComment = ratingComment;
    }

    public String getRatingBy() {
        return ratingBy;
    }

    public void setRatingBy(String ratingBy) {
        this.ratingBy = ratingBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Driver getFkeyDriverId() {
        return fkeyDriverId;
    }

    public void setFkeyDriverId(Driver fkeyDriverId) {
        this.fkeyDriverId = fkeyDriverId;
    }

    public Passenger getFkeyPassengerId() {
        return fkeyPassengerId;
    }

    public void setFkeyPassengerId(Passenger fkeyPassengerId) {
        this.fkeyPassengerId = fkeyPassengerId;
    }

    public Trip getFkeyTripId() {
        return fkeyTripId;
    }

    public void setFkeyTripId(Trip fkeyTripId) {
        this.fkeyTripId = fkeyTripId;
    }


	public Boolean getIsIssues() {
		return isIssues;
	}


	public void setIsIssues(Boolean isIssues) {
		this.isIssues = isIssues;
	}
    
}
