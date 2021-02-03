package com.eatThat.application.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.eatThat.application.model.User;

@Mapper
public interface UserMapper {

	@Insert("insert into users(first_name,last_name,email,password,active,registration_otp,create_date,modified_date,deleted,diet_plan_id) values(#{firstName},#{lastName}, #{email},#{password},#{active},#{registrationOtp},#{createDate},#{modifiedDate},#{deleted},#{dietPlanId})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn="id")
	public int insertUser(User user);

	@Select("select * from users where id=#{userId}")
	public User getUser(int userId);

	@Select("select * from users where  email=#{email} AND password=#{password} limit 1")
	public User Login(User user);

	@Update("update users set password=#{password} where email=#{email} AND deleted=0 and active=1")
	public boolean changePassword(User user);
	
	@Select("select * from users where email=#{email} and deleted=0 limit 1")
	public User getUserbyEmail(String email);

	@Update("update users set active=1 where email=#{email}")
	public boolean activateAccount(User user);
	
	@Update("update users set diet_plan_id=#{dietPlanId} where email=#{email}")
	public Boolean updatePlan(User user);
}
