package com.eatThat.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatThat.application.dao.UserDao;
import com.eatThat.application.model.User;

@Service("account")
public class AccountService {
	
	@Autowired
	UserDao UserDao;
	
	public User registerUser(User usr)
	{
		
		if(UserDao.getUserbyEmail(usr.getEmail()) == null)
		{
			int id = UserDao.insertUser(usr);		
			return UserDao.getUser(id);
		}
		else
		{
			return new User();
		}
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return UserDao.login(user);
	}

	public User getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean changePassword(User user) {
		// TODO Auto-generated method stub
		return UserDao.changePassword(user);
	}

}
