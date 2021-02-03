package com.eatThat.application.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eatThat.application.model.DietPlans;
import com.eatThat.application.model.FoodCategories;
import com.eatThat.application.model.FoodItem;
import com.eatThat.application.model.Response;
import com.eatThat.application.services.DietPlanService;

@RestController
@RequestMapping("/dietPlan")
public class DietPlanController {

	
	Response<T> response = new Response<T>();

	@Autowired
	DietPlanService dietPlanService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);


	@RequestMapping(method = RequestMethod.POST, path = "/getFoodItems", consumes = "application/json")
	public Response<T> getFoodItems(@RequestBody FoodItem foodItems) {
		
		ArrayList<FoodItem> items = (ArrayList<FoodItem>) dietPlanService.getAllItems(foodItems);
		
	//	if (items.size() > 0)
		//{
			response.setData(items);
			response.setMessage("success");
			response.setStatus("00");
			
//		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/getCategories")
	public Response<T> getCategories() {
		
		ArrayList<FoodCategories> items = (ArrayList<FoodCategories>) dietPlanService.getCategories();
		
		if (items.size() > 0)
		{
			response.setCategories(items);
			response.setMessage("succes");
			response.setStatus("00");
			
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/getDietPlans")
	public Response<T> getDietPlans() {
		
		ArrayList<DietPlans> items = (ArrayList<DietPlans>) dietPlanService.getDietPlans();
		
		if (items.size() > 0)
		{
			response.setDietPlans(items);
			response.setMessage("succes");
			response.setStatus("00");
			
		}
		return response;
	}
}
