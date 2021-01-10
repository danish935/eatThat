package com.eatThat.application.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAlias;
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
	
	public void setData(ArrayList<FoodItem> footItem) {
		this.ResponseData = (T) footItem;
	}
	
	public void setDietPlans(ArrayList<DietPlans> dietPlans) {
		this.ResponseData = (T) dietPlans;
	}
	
	public void setCategories(ArrayList<FoodCategories> foodCategories) {
		this.ResponseData = (T) foodCategories;
	}
	
	
	

}
