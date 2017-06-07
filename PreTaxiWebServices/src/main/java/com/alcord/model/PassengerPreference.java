/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "passenger_preference")
public class PassengerPreference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "fkey_address_id", referencedColumnName = "id")
    @ManyToOne
    private Address fkeyAddressId;
    @JoinColumn(name = "fkey_passenger_id", referencedColumnName = "id")
    @ManyToOne
    private Passenger fkeyPassengerId;

    public PassengerPreference() {
    }

    public PassengerPreference(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    public Address getFkeyAddressId() {
        return fkeyAddressId;
    }

    public void setFkeyAddressId(Address fkeyAddressId) {
        this.fkeyAddressId = fkeyAddressId;
    }

    public Passenger getFkeyPassengerId() {
        return fkeyPassengerId;
    }

    public void setFkeyPassengerId(Passenger fkeyPassengerId) {
        this.fkeyPassengerId = fkeyPassengerId;
    }

}
