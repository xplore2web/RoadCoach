package com.ajit.modelmappers;

import java.io.Serializable;

public class UserLoginDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4232708300719647981L;

//	private String id;
//	private String userName; 
	private String emailId;
//	private String firstName;
//	private String lastName;
	private String password;
//	private String userRole;
	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getUserRole() {
//		return userRole;
//	}
//	public void setUserRole(String userRole) {
//		this.userRole = userRole;
//	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
