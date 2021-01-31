package com.eatThat.application.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtility {
	
	 @Autowired
	    private Environment env;

	    public String getProperty(String pPropertyKey) {
	        return env.getProperty(pPropertyKey);
	    }

}
