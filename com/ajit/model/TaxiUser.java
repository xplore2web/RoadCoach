package com.ajit.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "taxi_user")
public class TaxiUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "user_name")
    private String userName;
    @Size(max = 50)
    @Column(name = "emai_id")
    private String emaiId;
    @Size(max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 20)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 50)
    @Column(name = "password")
    private String password;
    @Size(max = 50)
    @Column(name = "user_role")
    private String userRole;

    public TaxiUser() {
    }

    public TaxiUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmaiId() {
        return emaiId;
    }

    public void setEmaiId(String emaiId) {
        this.emaiId = emaiId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
