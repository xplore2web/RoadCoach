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
@Table(name = "passenger")
public class Passenger implements Serializable {


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
	@Column(name = "first_name")
	private String firstName;
	@Size(max = 50)
	@Column(name = "last_name")
	private String lastName;
	@Size(max = 50)
	@Column(name = "photo")
	private String photo;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@JoinColumn(name = "fkey_account_id", referencedColumnName = "id")
	@ManyToOne
	private Account fkeyAccountId;
	@JoinColumn(name = "fkey_address_id", referencedColumnName = "id")
	@ManyToOne
	private Address fkeyAddressId;

	public Passenger() {
	}

   
	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public Account getFkeyAccountId() {
		return fkeyAccountId;
	}

	public void setFkeyAccountId(Account fkeyAccountId) {
		this.fkeyAccountId = fkeyAccountId;
	}

	public Address getFkeyAddressId() {
		return fkeyAddressId;
	}

	public void setFkeyAddressId(Address fkeyAddressId) {
		this.fkeyAddressId = fkeyAddressId;
	}
}
