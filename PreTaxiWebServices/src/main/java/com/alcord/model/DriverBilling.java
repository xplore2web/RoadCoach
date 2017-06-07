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
@Table(name = "driver_billing")
public class DriverBilling implements Serializable {

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
    @Column(name = "total")
    private Double total;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "fkey_billing_id", referencedColumnName = "id")
    @ManyToOne
    private Billing fkeyBillingId;
    @JoinColumn(name = "fkey_company_driver_share_id", referencedColumnName = "id")
    @ManyToOne
    private CompanyDriverShare fkeyCompanyDriverShareId;

    public DriverBilling() {
    }

    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public Billing getFkeyBillingId() {
        return fkeyBillingId;
    }

    public void setFkeyBillingId(Billing fkeyBillingId) {
        this.fkeyBillingId = fkeyBillingId;
    }

    public CompanyDriverShare getFkeyCompanyDriverShareId() {
        return fkeyCompanyDriverShareId;
    }

    public void setFkeyCompanyDriverShareId(CompanyDriverShare fkeyCompanyDriverShareId) {
        this.fkeyCompanyDriverShareId = fkeyCompanyDriverShareId;
    }

}
