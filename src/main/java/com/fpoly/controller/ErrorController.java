package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {

	@GetMapping("/login/security/error")
	public String showErrorPage(@RequestParam(value = "blocked", required = false) String blocked, Model model) {
	    System.out.println("==> Controller /login/security/error được gọi. blocked = " + blocked);
	    model.addAttribute("blocked", blocked != null);
	    return "error";
	}
}


