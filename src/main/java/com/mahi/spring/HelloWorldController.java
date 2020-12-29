package com.mahi.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@RequestMapping("/")
	public String helloWorld() {
		return "Hello Java World!!!";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Java World!!!";
	}
}
