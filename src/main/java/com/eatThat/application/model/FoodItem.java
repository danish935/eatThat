package com.eatThat.application.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodItem {
	int id;
	String name;
	String servingSize;
	String servingWeight;
	String searchKey;
	
	int categoryId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int dietPlanId;
	
	@JsonIgnore
	@JsonProperty(value = "searchKey")
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	List<NutritionInfo2> NutritionInfo = new ArrayList<NutritionInfo2>();
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	
	public List<NutritionInfo2> getNutritionInfo() {
		return NutritionInfo;
	}
	public void setNutritionInfo(List<NutritionInfo2> info) {
		NutritionInfo = info;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	String planName;
	String categoryName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getDietPlanId() {
		return dietPlanId;
	}
	public void setDietPlanId(int dietPlanId) {
		this.dietPlanId = dietPlanId;
	}
	public String getServingSize() {
		return servingSize;
	}
	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}
	public String getServingWeight() {
		return servingWeight;
	}
	public void setServingWeight(String servingWeight) {
		this.servingWeight = servingWeight;
	}
	
}
