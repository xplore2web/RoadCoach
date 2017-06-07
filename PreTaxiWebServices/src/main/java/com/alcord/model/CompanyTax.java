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
@Table(name = "company_tax")
public class CompanyTax implements Serializable {

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
    @Column(name = "service_tax")
    private Double serviceTax;
    @Column(name = "swacch_bharath_cess")
    private Double swacchBharathCess;
    @Column(name = "krishi_kalyan_cess")
    private Double krishiKalyanCess;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIME)
    private Date createdAt;

    public CompanyTax() {
    }

    
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public Double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(Double serviceTax) {
        this.serviceTax = serviceTax;
    }

    public Double getSwacchBharathCess() {
        return swacchBharathCess;
    }

    public void setSwacchBharathCess(Double swacchBharathCess) {
        this.swacchBharathCess = swacchBharathCess;
    }

    public Double getKrishiKalyanCess() {
        return krishiKalyanCess;
    }

    public void setKrishiKalyanCess(Double krishiKalyanCess) {
        this.krishiKalyanCess = krishiKalyanCess;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
