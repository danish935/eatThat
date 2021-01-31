package com.eatThat.application.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.eatThat.application.model.User;

@Mapper
public interface UserMapper {

	@Insert("insert into users(first_name,last_name,email,password,active,email_verified,create_date,modified_date,deleted,diet_plan) values(#{firstName},#{lastName}, #{email},#{password},#{active},#{emailVerified},#{createDate},#{modifiedDate},#{deleted},#{dietPlan})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn="id")
	public int insertUser(User user);

	@Select("select * from users where id=#{userId}")
	public User getUser(int userId);

	@Select("select * from users where  email=#{email} AND password=#{password} and active=1 limit 1")
	public User Login(User user);

	@Update("update users set password=#{password} where email=#{email} AND deleted=0 and active=1")
	public boolean changePassword(User user);
	
	@Select("select email from users where email=#{email} and deleted=0 and active=1")
	public User getUserbyEmail(String email);
}
