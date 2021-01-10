package com.eatThat.application.dao.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.poi.ss.formula.functions.T;

import com.eatThat.application.model.DietPlans;
import com.eatThat.application.model.FoodCategories;
import com.eatThat.application.model.FoodItem;
import com.eatThat.application.model.NutritionInfo;
import com.eatThat.application.model.NutritionInfo2;

@Mapper
public interface DietPlanMapper {

	@Select ("select fi.*, dl.plan_name, cat.category_name from food_items fi \r\n"
			+ "join diet_plans dl \r\n"
			+ "on fi.diet_plan_id = dl.id\r\n"
			+ "join diet_categories cat\r\n"
			+ "on fi.category_id = cat.id\r\n"
			+ "AND\r\n"
			+ "fi.category_id=#{categoryId} AND dl.id =#{dietPlanId};")
	public ArrayList<FoodItem> getAllItems(FoodItem foodItems);

	@Select("select * from food_nutritions_info where food_items_id=#{id} OR name=#{name}")
	public List<NutritionInfo2> getNutritionInfo(@Param(value = "id") int id, @Param(value = "name") String name);

	@Select("select * from diet_categories")
	public ArrayList<FoodCategories> getCategories();
	
	@Select("select * from diet_plans")
	public ArrayList<DietPlans> getDietPlans();

	@Insert("insert into diet_plans(plan_name) values(#{planName})")
	@Options(useGeneratedKeys = true,keyProperty = "dietId",keyColumn="id")
	public Integer insertHeader(DietPlans diet);

	@Insert("insert into diet_categories(category_name) values(#{categoryName})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertCategory(FoodCategories value);
	
	@Delete("truncate table ${tableName}")
	public void truncateTables(@Param(value = "tableName") String tableName);

	@Insert("insert into food_items(name, serving_size, serving_weight, category_id, diet_plan_id) values(#{name},#{servingSize},#{servingWeight},#{categoryId},#{dietPlanId})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn="id")
	public int insertFoodItem(FoodItem item);

	@Insert("insert into food_nutritions_info(Nutrition, weight, food_items_id, name) values(#{nutrition},#{weight},#{foodItemId},#{foodItemName})")
	public void insertNutritionInfo(@Param(value = "nutrition") String nutrition,@Param(value = "weight") String weight, @Param(value = "foodItemId") int foodItemId, @Param(value = "foodItemName") String foodItemName);
}
