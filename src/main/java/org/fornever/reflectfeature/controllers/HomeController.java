package org.fornever.reflectfeature.controllers;

import org.fornever.reflectfeature.annotations.RequestMapping;

@RequestMapping(path = "/home")
public class HomeController {

	@RequestMapping(path = "/hello", method = "GET")
	public String hello() {
		return "hello world";
	}

}
