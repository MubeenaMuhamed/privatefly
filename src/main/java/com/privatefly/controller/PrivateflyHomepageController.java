package com.privatefly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrivateflyHomepageController {

	@RequestMapping("/")
	public String privateflyHomepage(){
		return "index";
	}
}