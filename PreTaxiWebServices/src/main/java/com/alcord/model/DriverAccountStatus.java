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
@Table(name = "driver_account_status")
public class DriverAccountStatus implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
	private UUID id;
    @Size(max = 50)
    @Column(name = "account_status_name")
    private String accountStatusName;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private Account createdBy;
    @Column(name = "approved")
    private Boolean approved;
    @JoinColumn(name = "approved_by", referencedColumnName = "id")
    @ManyToOne
    private Account approvedBy;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fkey_driver_id", referencedColumnName = "id")
    @ManyToOne
    private Driver fkeyDriverId;

    public DriverAccountStatus() {
    }
    public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }

    
    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Account getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Account createdBy) {
		this.createdBy = createdBy;
	}
	public Account getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Account approvedBy) {
		this.approvedBy = approvedBy;
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
}
