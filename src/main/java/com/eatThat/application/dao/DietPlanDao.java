package com.eatThat.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eatThat.application.dao.mapper.DietPlanMapper;
import com.eatThat.application.dao.mapper.UserMapper;
import com.eatThat.application.model.DietPlans;
import com.eatThat.application.model.FoodCategories;
import com.eatThat.application.model.FoodItem;
import com.eatThat.application.model.NutritionInfo;
import com.eatThat.application.model.NutritionInfo2;
import com.eatThat.application.model.User;

@Component
public class DietPlanDao {
	

	@Autowired
	DietPlanMapper dietPlanMapper;

	public ArrayList<FoodItem> getAllItemsByDietPlanANDCategory(FoodItem foodItems) {
		return dietPlanMapper.getAllItemsByDietPlanANDCategory(foodItems);
	}

	public List<NutritionInfo2> getNutritionInfo(int id, String name) {
		return dietPlanMapper.getNutritionInfo(id, name);
	}

	public ArrayList<FoodCategories> getCategories() {
		return dietPlanMapper.getCategories();
	}

	public ArrayList<DietPlans> getDietPlans() {
		return dietPlanMapper.getDietPlans();
	}

	public Integer insertHeader(DietPlans value) {
		return dietPlanMapper.insertHeader(value);
	}

	public int insertCategory(FoodCategories string) {
		return dietPlanMapper.insertCategory(string);
	}

	public void truncateTables() {
		dietPlanMapper.truncateTables("diet_plans");
		dietPlanMapper.truncateTables("diet_categories");
		dietPlanMapper.truncateTables("food_nutritions_info");
		dietPlanMapper.truncateTables("food_items");
	}

	public int insertFoodItem(FoodItem item) {
		return dietPlanMapper.insertFoodItem(item);
	}

	public void insertNutritionInfo(String key, String string, int foodItemId, String foodItemName) {
		 dietPlanMapper.insertNutritionInfo(key, string, foodItemId, foodItemName);

	}

	public ArrayList<FoodItem> getAllItemsByDietPlan(FoodItem foodItems) {
		return dietPlanMapper.getAllItemsByDietPlan(foodItems);
	}

	public ArrayList<FoodItem> getAllItemsBySearchKey(FoodItem foodItems) {
		return dietPlanMapper.getAllItemsBySearchKey(foodItems);
	}

	public ArrayList<FoodItem> getAllItems() {
		// TODO Auto-generated method stub
		return dietPlanMapper.getAllItems();
	}

}
