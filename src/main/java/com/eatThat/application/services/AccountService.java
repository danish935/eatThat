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
		int registrationotp = 0;
		
		User temp = UserDao.getUserbyEmail(usr.getEmail());
		
		if(temp == null)
		{
			registrationotp = (int)(Math.random()*1000000);
			usr.setRegistrationOtp(Integer.toString(registrationotp));
			int id = UserDao.insertUser(usr);	
			return UserDao.getUser(usr.getId());
		}
		else
		{
			return new User();
		}
	}

	public AccountInfo login(User user) {
	
		User usr =  UserDao.login(user);
		
		if (usr == null)
			return AccountInfo.INVALIDLOGIN;
		else if (usr.getActive() == 0)
			return AccountInfo.INACTIVE;
		else if (usr != null && usr.getActive() == 1)
			return AccountInfo.SUCCESS;
		
		return AccountInfo.INVALIDLOGIN;
	}

	public User getUser(User user) {
		return UserDao.login(user);
	}

	public AccountInfo changePassword(User user, boolean forgotPasswordFlow) {
		// TODO Auto-generated method stub
		
		if(UserDao.getUserbyEmail(user.getEmail()) != null)
		{
			int randomPassword = 0;
			if (forgotPasswordFlow)
			{
				randomPassword = (int)(Math.random()*1000000);
				user.setPassword(Integer.toString(randomPassword));
				UserDao.changePassword(user);
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

	public AccountInfo verifyOtp(User user) {
		// TODO Auto-generated method stub
		
		User temp = UserDao.getUserbyEmail(user.getEmail());
		if(temp != null)
		{
			if(temp.getRegistrationOtp().equals(user.getRegistrationOtp()))
			{
				UserDao.activateAccount(user);
				return AccountInfo.OTPVERIFIED;
			}
			
		}
		else
		{
			return AccountInfo.OTPVERIFIEDFAIL;
		}
		return AccountInfo.OTPVERIFIEDFAIL;
	}

	public AccountInfo updatePlan(User user) {
		User temp = UserDao.getUserbyEmail(user.getEmail());
		if(temp != null)
		{
				UserDao.updatePlan(user);
				return AccountInfo.PLANUPDATED;
		}
		else
		{
			return AccountInfo.USERNOTEXISTS;
		}
		
	}

}
