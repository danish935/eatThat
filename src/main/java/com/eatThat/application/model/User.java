package com.eatThat.application.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	String firstName;
	String lastName;
	String email;
	String password;
	int deleted;
	int active;
	String registrationOtp;
	Date createDate;
	Date modifiedDate;
	String dietPlan;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int id;
	
	public String getDietPlan() {
		return dietPlan;
	}

	public void setDietPlan(String dietPlan) {
		this.dietPlan = dietPlan;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRegistrationOtp() {
		return registrationOtp;
	}

	@JsonIgnore
	@JsonProperty(value = "registrationOtp")
	public void setRegistrationOtp(String registrationOtp) {
		this.registrationOtp = registrationOtp;
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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
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

	@JsonIgnore
	@JsonProperty(value = "password")
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