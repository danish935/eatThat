package com.eatThat.application.rest;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eatThat.application.model.Response;
import com.eatThat.application.model.User;
import com.eatThat.application.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	
	
	@Autowired
	AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    
	Response<T> response = new Response<T>();


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
			response.setMessage("User Register Successfully");
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

		User usr = accountService.login(user);
		
		
		if (usr !=null)
		{
			response.setStatus("00");
			response.setMessage("true");
			response.setData(usr);
		}
		else
		{
			response.setStatus("01");
			response.setMessage("false");
			response.setData(new User());
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/changePassword", consumes = "application/json")
	//public Response<T> login(@RequestParam String email, @RequestParam String password) {
		
		public Response<T> changePassword(@RequestBody User user) {

		boolean updated = accountService.changePassword(user);
	
		if (updated)
		{
			response.setStatus("00");
			response.setMessage("true");
			response.setData();
		}
		else
		{
			response.setStatus("01");
			response.setMessage("false");
			response.setData();
		}
		return response;
	}

}
