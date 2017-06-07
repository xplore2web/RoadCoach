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
@Table(name = "driver_slab")
public class DriverSlab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
	private UUID id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "slab_one_rate")
    private Double slabOneRate;
    @Column(name = "slab_one_distance")
    private Double slabOneDistance;
    @Column(name = "charge_per_min")
    private Double chargePerMin;
    @Column(name = "slab_two_rate")
    private Double slabTwoRate;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fkey_driver_id", referencedColumnName = "id")
    @ManyToOne
    private Driver fkeyDriverId;
    @JoinColumn(name = "fkey_master_base_id", referencedColumnName = "id")
    @ManyToOne
    private MasterBaseFare fkeyMasterBaseId;
    @JoinColumn(name = "fkey_vehicle_id", referencedColumnName = "id")
    @ManyToOne
    private Vehicle fkeyVehicleId;

    public DriverSlab() {
    }

     
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public Double getSlabOneRate() {
        return slabOneRate;
    }

    public void setSlabOneRate(Double slabOneRate) {
        this.slabOneRate = slabOneRate;
    }

    public Double getSlabOneDistance() {
        return slabOneDistance;
    }

    public void setSlabOneDistance(Double slabOneDistance) {
        this.slabOneDistance = slabOneDistance;
    }

    public Double getChargePerMin() {
        return chargePerMin;
    }

    public void setChargePerMin(Double chargePerMin) {
        this.chargePerMin = chargePerMin;
    }

    public Double getSlabTwoRate() {
        return slabTwoRate;
    }

    public void setSlabTwoRate(Double slabTwoRate) {
        this.slabTwoRate = slabTwoRate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Driver getFkeyDriverId() {
        return fkeyDriverId;
    }

    public void setFkeyDriverId(Driver fkeyDriverId) {
        this.fkeyDriverId = fkeyDriverId;
    }

    public MasterBaseFare getFkeyMasterBaseId() {
        return fkeyMasterBaseId;
    }

    public void setFkeyMasterBaseId(MasterBaseFare fkeyMasterBaseId) {
        this.fkeyMasterBaseId = fkeyMasterBaseId;
    }

    public Vehicle getFkeyVehicleId() {
        return fkeyVehicleId;
    }

    public void setFkeyVehicleId(Vehicle fkeyVehicleId) {
        this.fkeyVehicleId = fkeyVehicleId;
    }

}
