package com.eatThat.application.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatThat.application.dao.DietPlanDao;
import com.eatThat.application.model.DietPlans;
import com.eatThat.application.model.FoodCategories;
import com.eatThat.application.model.FoodItem;
import com.eatThat.application.model.NutritionInfo;
import com.eatThat.application.model.NutritionInfo2;

@Service
public class DietPlanService {

	
	ArrayList<FoodItem> items = new ArrayList<FoodItem>();
	List<NutritionInfo2> info = new ArrayList<NutritionInfo2>();
	
	@Autowired
	DietPlanDao dietPlanDao;
	public ArrayList<FoodItem> getAllItems(FoodItem foodItems) {
		
		 
		items = dietPlanDao.getAllItems(foodItems);
		for(int i = 0 ; i< items.size(); i++)
		{
			FoodItem item = items.get(i);
			
			info = dietPlanDao.getNutritionInfo(item.getId(), item.getName());
			
			item.setNutritionInfo(info);
		}

		return items;
	}
	public ArrayList<FoodCategories> getCategories() {
		// TODO Auto-generated method stub
		return dietPlanDao.getCategories();
	}
	public ArrayList<DietPlans> getDietPlans() {
		// TODO Auto-generated method stub
		return dietPlanDao.getDietPlans();
	}
	public Map<String, Integer> insertDietPlans(List<String> headers) {
		// TODO Auto-generated method stub
		Map<String, Integer> temp = new HashMap<String, Integer>();
		for(int count = 0 ; count < headers.size(); count ++ )
		{
			if(headers.get(count) != null) {
				DietPlans diet = new DietPlans();
				diet.setPlanName(headers.get(count));
				Integer id = dietPlanDao.insertHeader(diet);
				temp.put(headers.get(count), diet.getDietId());
			}
			
		}
		return temp;
	}
	public Map<String, Integer> insertCategories(List<String> categories) {
		// TODO Auto-generated method stub
		Map<String, Integer> temp = new HashMap<String, Integer>();
		for(int count = 0 ; count < categories.size(); count ++ )
		{
			if(categories.get(count) != null) {
				FoodCategories cat = new FoodCategories();
				cat.setCategory(categories.get(count));
			int id = dietPlanDao.insertCategory(cat);
			temp.put(categories.get(count), cat.getId());
			}
		}
		return temp;
	}
	public void truncateTables() {
		// TODO Auto-generated method stub
		dietPlanDao.truncateTables();
	}
	public int insertFoodItem(String itemName, String servingSize, String servingWeight, Integer categoryId,
			Integer planId) {
		// TODO Auto-generated method stub
		FoodItem temp = new FoodItem();
		temp.setCategoryId(categoryId);
		temp.setName(itemName);
		temp.setServingSize(servingSize);
		temp.setServingWeight(servingWeight);
		temp.setDietPlanId(planId);
		
		dietPlanDao.insertFoodItem(temp);
		
		return 	temp.getId();	

	}
	public void insertNutritionInfo(Map<String, String> nutritionMap, int foodItemId, String foodItemName) {
		// TODO Auto-generated method stub
		
		for (String key : nutritionMap.keySet()) {
			   
				dietPlanDao.insertNutritionInfo(key,nutritionMap.get(key).toString(),foodItemId, foodItemName);

			}
		
//		for(int count = 0 ; count <= nutritionMap.size(); count ++ )
//		{
//			dietPlanDao.insertNutritionInfo(getKey(nutritionMap, nutritionMap.get(count)),nutritionMap.get(count),foodItemId, foodItemName);
//			
//		}
	}
	
	public  String getKey(Map<String, String> mapref, String value) {
	    String key = "";
	    for (Map.Entry<String, String> map : mapref.entrySet()) {
	        if (map.getValue().toString().equals(value)) {
	            key = map.getKey();
	        }
	    }
	    return key;
	}

}
