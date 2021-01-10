package com.eatThat.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {
	
	@RequestMapping(value = "/index")
	   public String index() {
	      return "index";
	   }
	
	@RequestMapping(value = "/home")
	   public String home() {
	      return "home";
	   }

}
