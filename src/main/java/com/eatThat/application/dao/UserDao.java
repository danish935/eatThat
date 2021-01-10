package com.eatThat.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eatThat.application.dao.mapper.UserMapper;
import com.eatThat.application.model.User;

@Component
public class UserDao {

	@Autowired
	UserMapper mapper;

	public int insertUser(User user) {
		int id = mapper.insertUser(user);
		return id;
	}

	public User getUser(int userId) {
		User user = mapper.getUser(userId);
		return user;
	}

	
	public User getUserbyEmail(String email) {
		User user = mapper.getUserbyEmail(email);
		return user;
	}
	public User login(User user) {
		// TODO Auto-generated method stub
		return mapper.Login(user);
		
	}

	public Boolean changePassword(User user) {
		// TODO Auto-generated method stub
		return mapper.changePassword(user);
	}
}
