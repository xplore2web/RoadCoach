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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "driver_vehicle_mapping")
public class DriverVehicleMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
	private UUID id;
	@Column(name = "is_current_vehicle")
	private Boolean isCurrentVehicle;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@JoinColumn(name = "fkey_driver_id", referencedColumnName = "id")
	@ManyToOne
	private Driver fkeyDriverId;
	@JoinColumn(name = "fkey_vehicle_id", referencedColumnName = "id")
	@ManyToOne
	private Vehicle fkeyVehicleId;

	public DriverVehicleMapping() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Boolean getIsCurrentVehicle() {
		return isCurrentVehicle;
	}

	public void setIsCurrentVehicle(Boolean isCurrentVehicle) {
		this.isCurrentVehicle = isCurrentVehicle;
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

	public Vehicle getFkeyVehicleId() {
		return fkeyVehicleId;
	}

	public void setFkeyVehicleId(Vehicle fkeyVehicleId) {
		this.fkeyVehicleId = fkeyVehicleId;
	}
}
