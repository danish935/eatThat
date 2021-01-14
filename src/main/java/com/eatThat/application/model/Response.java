package com.eatThat.application.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response<T> {
	private String status;
    private String message;
    private T ResponseData;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@SuppressWarnings("unchecked")
	public void setData(User usr) {
		this.ResponseData = (T) usr;
	}
	@JsonProperty("ResponseData")
	public T getResponseData() {
		return ResponseData;
	}
	public void setData() {
		this.ResponseData = null;
	}
	
	@SuppressWarnings("unchecked")
	public void setData(ArrayList<FoodItem> footItem) {
		this.ResponseData = (T) footItem;
	}
	
	@SuppressWarnings("unchecked")
	public void setDietPlans(ArrayList<DietPlans> dietPlans) {
		this.ResponseData = (T) dietPlans;
	}
	
	@SuppressWarnings("unchecked")
	public void setCategories(ArrayList<FoodCategories> foodCategories) {
		this.ResponseData = (T) foodCategories;
	}
	
	
	

}
