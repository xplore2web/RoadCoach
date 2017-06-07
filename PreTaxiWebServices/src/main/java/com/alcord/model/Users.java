package com.alcord.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author akmal
 */

@Entity
@Table(name = "users_taxi")
public class Users implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(generator = "uuid2")
	    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	    @Column(columnDefinition = "BINARY(16)",name = "id")
	    @Type(type="pg-uuid")
	    @Basic(optional = false)
	    @NotNull
	    private UUID id;
	    @Size(max = 100)
	    @Column(name = "name")
	    private String name;
	    @Size(max = 50)
	    @Column(name = "email_id")
	    private String emailId;
	    @Size(max = 500)
	    @Column(name = "password")
	    private String password;
	    @Column(name = "created_at")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdAt;
	    

	    public Date getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}


		public Users() {
	    }


		public UUID getId() {
			return id;
		}


		public void setId(UUID id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getEmailId() {
			return emailId;
		}


		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	    
	    
}
