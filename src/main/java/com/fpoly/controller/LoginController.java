package com.fpoly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.entity.Account;
import com.fpoly.entity.Authority;
import com.fpoly.security.CustomerOAuth2User;
import com.fpoly.service.AccountService;
import com.fpoly.service.AuthorityService;
import com.fpoly.service.MyUserDetailService;
import com.fpoly.service.RoleService;

import jakarta.servlet.http.HttpServletRequest;




@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	MyUserDetailService my;
	
    @GetMapping("sign-in")
    public String signIn() {
        return "/security/login";
    }
    
    
    @GetMapping("security/success")
    public String success(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công");
        if(customerOAuth2User() != null) {
        	Optional<Account> acc = accountService.findByUsername(customerOAuth2User().getUsername());
            if(acc.isEmpty()){
            	Account user = new Account();
            	user.setUsername(customerOAuth2User().getUsername());
            	user.setPassword("123");
            	user.setFullname(customerOAuth2User().getName());
            	user.setEmail(customerOAuth2User().getEmail());
            	user.setPhoto("user.png");
            	accountService.create(user);
            	Authority auth = new Authority();
            	auth.setAccount(user);
            	auth.setRole(roleService.findById("CUST"));
            	authorityService.create(auth);
            }
        }
        return "redirect:/product/list";
    }
    
   




    
    @GetMapping("security/out")
    public String logout(RedirectAttributes redirectAttributes) {
        // Xóa người dùng khỏi context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.clearContext(); // Đảm bảo logout người dùng khỏi context
        }
        
        // Thêm thông báo logout thành công
        redirectAttributes.addFlashAttribute("message", "Đăng xuất thành công");
        
        // Chuyển hướng về trang chủ hoặc trang đăng nhập
        return "redirect:/product/list";
    }
    @GetMapping("security/out/success")
    public String outSuccess(Model model) {
    	model.addAttribute("message", "Đăng xuất thành công");
    	return "/security/login";
    }
    
    public CustomerOAuth2User customerOAuth2User() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomerOAuth2User customerOAuth2User = null;
		if (authentication != null && authentication.getPrincipal() instanceof CustomerOAuth2User) {
			customerOAuth2User = (CustomerOAuth2User) authentication.getPrincipal();
		}
		return customerOAuth2User;
	}
}
