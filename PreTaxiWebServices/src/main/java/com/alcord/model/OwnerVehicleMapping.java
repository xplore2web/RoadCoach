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
@Table(name = "owner_vehicle_mapping")
public class OwnerVehicleMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
	private UUID id;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@JoinColumn(name = "fkey_owner_id", referencedColumnName = "id")
	@ManyToOne
	private Owner fkeyOwnerId;
	@JoinColumn(name = "fkey_vehicle_id", referencedColumnName = "id")
	@ManyToOne
	private Vehicle fkeyVehicleId;

	public OwnerVehicleMapping() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public Owner getFkeyOwnerId() {
		return fkeyOwnerId;
	}

	public void setFkeyOwnerId(Owner fkeyOwnerId) {
		this.fkeyOwnerId = fkeyOwnerId;
	}

	public Vehicle getFkeyVehicleId() {
		return fkeyVehicleId;
	}

	public void setFkeyVehicleId(Vehicle fkeyVehicleId) {
		this.fkeyVehicleId = fkeyVehicleId;
	}
}
