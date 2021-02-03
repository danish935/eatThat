package com.eatThat.application.rest;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eatThat.application.model.AccountInfo;
import com.eatThat.application.model.Response;
import com.eatThat.application.model.User;
import com.eatThat.application.services.AccountService;
import com.eatThat.application.util.EmailUtil;

@RestController
@RequestMapping("/account")
public class AccountController {

	
	
	@Autowired
	AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    
    
	Response<T> response = new Response<T>();
	@Autowired
	EmailUtil emailUtil;


	@RequestMapping(method = RequestMethod.GET)
	public String sayHello() {
		logger.info("hello");
		return "Hello World!";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/registerUser", consumes = "application/json")
	public Response<T> registerUser(@RequestBody User user) {
		
		User temp = accountService.registerUser(user);
		if (temp.getEmail() != null) {
			response.setData(temp);
			response.setStatus("00");
			response.setMessage("User Register Successfully.");
			emailUtil.sendRegistrationEmail(temp);
		}
		else{
			response.setData(new User());
			response.setStatus("01");
			response.setMessage("User Register Failed");
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, path = "/login", consumes = "application/json")
	//public Response<T> login(@RequestParam String email, @RequestParam String password) {
		
		public Response<T> login(@RequestBody User user) {
		
		AccountInfo info = accountService.login(user);
		
		
		if (info == AccountInfo.SUCCESS)
		{
			response.setStatus("00");
			response.setMessage("Login Successfully");
			response.setData(accountService.getUser(user));
		}
		else if(info == AccountInfo.INVALIDLOGIN)
		{
			response.setStatus("01");
			response.setMessage("Invalid username/password");
			response.setData(new User());
		}
		else if (info == AccountInfo.INACTIVE)
		{
			response.setStatus("02");
			response.setMessage("User is inactive. Please activate account using OTP sent on email.");
			response.setData(new User());
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/changePassword", consumes = "application/json")
	//public Response<T> login(@RequestParam String email, @RequestParam String password) {
		
		public Response<T> changePassword(@RequestBody User user) {
		
		boolean  forgotPasswordFlow = false;

	 AccountInfo info = accountService.changePassword(user, forgotPasswordFlow);
	
		if (info == AccountInfo.SUCCESS)
		{
			response.setStatus("00");
			response.setMessage("Password Changed sucessfully");
			response.setData();
		}
		else if(info == AccountInfo.USERNOTEXISTS)
		{
			response.setStatus("01");
			response.setMessage("User not exists");
			response.setData();
		}
		else if(info == AccountInfo.RETRY)
		{
			response.setStatus("02");
			response.setMessage("Please try again later");
			response.setData();
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/forgotPassword", consumes = "application/json")
	//public Response<T> login(@RequestParam String email, @RequestParam String password) {
		
		public Response<T> forgotPassword(@RequestBody User user) {
		boolean  forgotPasswordFlow = true;
		
		AccountInfo info = accountService.changePassword(user, forgotPasswordFlow);
	
		if (info == AccountInfo.SUCCESS)
		{
			response.setStatus("00");
			response.setMessage("Please find details in eamil to proceed further.");
			response.setData();
		}
		else if(info == AccountInfo.USERNOTEXISTS)
		{
			response.setStatus("01");
			response.setMessage("User not exists");
			response.setData();
		}
		else if(info == AccountInfo.RETRY)
		{
			response.setStatus("02");
			response.setMessage("Please try again later");
			response.setData();
		}
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/verifyOtp", consumes = "application/json")
		public Response<T> verifyOtp(@RequestBody User user) {
		
		AccountInfo info = accountService.verifyOtp(user);
	
		if (info == AccountInfo.OTPVERIFIED)
		{
			response.setStatus("00");
			response.setMessage("Account activated successfully.");
			response.setData();
		}
		else
		{
			response.setStatus("01");
			response.setMessage("Account activation fail. Please try again");
			response.setData();
		}
		
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/updatePlan", consumes = "application/json")
	public Response<T> updatePlan(@RequestBody User user) {
	
	AccountInfo info = accountService.updatePlan(user);

	if (info == AccountInfo.PLANUPDATED)
	{
		response.setStatus("00");
		response.setMessage("Diet Plan updated successfully.");
		response.setData();
	}
	else
	{
		response.setStatus("01");
		response.setMessage("Plan updation failed");
		response.setData();
	}
	
	return response;
}

}
