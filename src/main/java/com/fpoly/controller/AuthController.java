package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.service.Oauth2Service;


@Controller
public class AuthController {
	@Autowired
	Oauth2Service oauth2Service;
	
	@RequestMapping("/auth/login/form")
	public String form() {
		return "auth/login";
	}
	
	@RequestMapping("/auth/login/success")
	public String  success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		 return "redirect:/product/list";
		
	}
	
	@RequestMapping("/auth/login/error")
	public String  error(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại");
		return "forward:/auth/login/form";
		
	}
	@RequestMapping("/auth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		oauth2Service.loginFromOAuth2(oauth2);
		 return "redirect:/product/list";
		
	}
	

}
