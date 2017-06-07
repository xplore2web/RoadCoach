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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "trip_points")
public class TripPoints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
    private UUID id;
    @Lob
    @Column(name = "longitude_latitude")
    private Object longitudeLatitude;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "fkey_trip_id", referencedColumnName = "id")
    @ManyToOne
    private Trip fkeyTripId;

    public TripPoints() {
    }


    public Object getLongitudeLatitude() {
        return longitudeLatitude;
    }

    public void setLongitudeLatitude(Object longitudeLatitude) {
        this.longitudeLatitude = longitudeLatitude;
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

    public Trip getFkeyTripId() {
        return fkeyTripId;
    }

    public void setFkeyTripId(Trip fkeyTripId) {
        this.fkeyTripId = fkeyTripId;
    }


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}
}
