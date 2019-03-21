package com.toni.ferreiro.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller("/locale")
public class LocaleController{
	
	public String locale(HttpServletRequest request) {
		String ultimaUrl = request.getHeader("referer");
		return "redirect:".concat(ultimaUrl);
	}

}
