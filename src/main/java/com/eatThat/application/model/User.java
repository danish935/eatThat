package com.eatThat.application.model;

import java.sql.Date;

public class User {
	String firstName;
	String lastName;
	String email;
	String password;
	Boolean deleted = false;
	Boolean active = true;
	Boolean emailVerified = true;
	Date createDate;
	Date modifiedDate;
	String dietPlan;
	
	public String getDietPlan() {
		return dietPlan;
	}

	public void setDietPlan(String dietPlan) {
		this.dietPlan = dietPlan;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




//	@Override
//	public String toString() {
//		return "User [userId=" + id + ", name=" + name + "]";
//	}

}