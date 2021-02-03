package com.eatThat.application.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.eatThat.application.model.User;
import com.eatThat.application.rest.AccountController;

@Component
public class EmailUtil {
	
	 @Autowired
	 private JavaMailSender javaMailSender;
	 
	 private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	    
	  public  void sendRegistrationEmail(User usr) {
   	
	    	
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(usr.getEmail());

	        msg.setSubject("Eat-That Registration Complete.");
	        msg.setText("Hello " + usr.getLastName() + " "+ usr.getFirstName() +"\n Welcome onboard on Eat That. Your account is created. use below credentials to login after account activation. \n Username: " + usr.getEmail() + "\n Password: " + usr.getPassword() + ". \n Use "+ usr.getRegistrationOtp()  + " as one time password to activate the account. \n \n Regards, \n\n Eat That Team.");

	        javaMailSender.send(msg);

	    }

	public void sendForgotPasswordEmail(User user) {
		// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Eat-That Forgot Password");
        msg.setText("Hello user! \n\n Please use below password to use the account. \n" + user.getPassword() + "\n \n Regards, \n\n Eat That Team.");

        javaMailSender.send(msg);
	}

}
