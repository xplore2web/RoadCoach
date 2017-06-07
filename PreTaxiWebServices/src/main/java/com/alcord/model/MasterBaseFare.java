/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "master_base_fare")
public class MasterBaseFare implements Serializable {

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
    @Column(name = "base_fare")
    private Double baseFare;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "fkeyMasterBaseId")
    private Collection<DriverSlab> driverSlabCollection;
    @JoinColumn(name = "fkey_city_id", referencedColumnName = "id")
    @ManyToOne
    private City fkeyCityId;
    @JoinColumn(name = "fkey_vehicle_type_id", referencedColumnName = "id")
    @ManyToOne
    private VehicleType fkeyVehicleTypeId;

    public MasterBaseFare() {
    }

    
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public Double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
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
