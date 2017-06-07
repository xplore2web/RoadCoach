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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "driver_document_status")
public class DriverDocumentStatus implements Serializable {

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
    @Column(name = "document_status_name")
    private String documentStatusName;
    @Lob
    @Column(name = "created_by")
    private Object createdBy;
    @Column(name = "approved")
    private Boolean approved;
    @Lob
    @Column(name = "approved_by")
    private Object approvedBy;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fkey_driver_id", referencedColumnName = "id")
    @ManyToOne
    private Driver fkeyDriverId;

    public DriverDocumentStatus() {
    }

    
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getDocumentStatusName() {
        return documentStatusName;
    }

    public void setDocumentStatusName(String documentStatusName) {
        this.documentStatusName = documentStatusName;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Object getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Object approvedBy) {
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
