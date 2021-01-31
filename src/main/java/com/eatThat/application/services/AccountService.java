package com.eatThat.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatThat.application.dao.UserDao;
import com.eatThat.application.model.AccountInfo;
import com.eatThat.application.model.User;
import com.eatThat.application.util.EmailUtil;

@Service("account")
public class AccountService {
	
	@Autowired
	UserDao UserDao;
	
	@Autowired
	EmailUtil emailUtil;
	
	public User registerUser(User usr)
	{
		
		if(UserDao.getUserbyEmail(usr.getEmail()) == null)
		{
			int id = UserDao.insertUser(usr);	
			return UserDao.getUser(usr.getId());
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

	public AccountInfo changePassword(User user, boolean forgotPasswordFlow) {
		// TODO Auto-generated method stub
		
		if(UserDao.getUserbyEmail(user.getEmail()) != null)
		{
			int randomPassword = 0;
			if (forgotPasswordFlow)
			{
				randomPassword = (int)(Math.random()*100000);
				user.setPassword(Integer.toString(randomPassword));
				emailUtil.sendForgotPasswordEmail(user);

			}
		}
		else
		{
			return AccountInfo.USERNOTEXISTS;
		}
			
		
		if (UserDao.changePassword(user))
			{
			return AccountInfo.SUCCESS;
			}
		else
		{
			return AccountInfo.RETRY;

		}
	}

}
