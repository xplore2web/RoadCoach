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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "master_slab")
public class MasterSlab implements Serializable {

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
    @Column(name = "slab_one_start")
    private Double slabOneStart;
    @Column(name = "slab_one_end")
    private Double slabOneEnd;
    @Column(name = "slab_one_distance")
    private Double slabOneDistance;
    @Column(name = "slab_two_start")
    private Double slabTwoStart;
    @Column(name = "slab_two_end")
    private Double slabTwoEnd;
    @Column(name = "charge_per_min")
    private Double chargePerMin;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fkey_city_id", referencedColumnName = "id")
    @ManyToOne
    private City fkeyCityId;
    @JoinColumn(name = "fkey_vehicle_type_id", referencedColumnName = "id")
    @ManyToOne
    private VehicleType fkeyVehicleTypeId;

    public MasterSlab() {
    }

    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public Double getSlabOneStart() {
        return slabOneStart;
    }

    public void setSlabOneStart(Double slabOneStart) {
        this.slabOneStart = slabOneStart;
    }

    public Double getSlabOneEnd() {
        return slabOneEnd;
    }

    public void setSlabOneEnd(Double slabOneEnd) {
        this.slabOneEnd = slabOneEnd;
    }

    public Double getSlabOneDistance() {
        return slabOneDistance;
    }

    public void setSlabOneDistance(Double slabOneDistance) {
        this.slabOneDistance = slabOneDistance;
    }

    public Double getSlabTwoStart() {
        return slabTwoStart;
    }

    public void setSlabTwoStart(Double slabTwoStart) {
        this.slabTwoStart = slabTwoStart;
    }

    public Double getSlabTwoEnd() {
        return slabTwoEnd;
    }

    public void setSlabTwoEnd(Double slabTwoEnd) {
        this.slabTwoEnd = slabTwoEnd;
    }

    public Double getChargePerMin() {
        return chargePerMin;
    }

    public void setChargePerMin(Double chargePerMin) {
        this.chargePerMin = chargePerMin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public City getFkeyCityId() {
        return fkeyCityId;
    }

    public void setFkeyCityId(City fkeyCityId) {
        this.fkeyCityId = fkeyCityId;
    }

    public VehicleType getFkeyVehicleTypeId() {
        return fkeyVehicleTypeId;
    }

    public void setFkeyVehicleTypeId(VehicleType fkeyVehicleTypeId) {
        this.fkeyVehicleTypeId = fkeyVehicleTypeId;
    }

    
}
