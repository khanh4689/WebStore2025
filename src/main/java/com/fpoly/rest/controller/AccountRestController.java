package com.fpoly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.entity.Account;
import com.fpoly.entity.Authority;
import com.fpoly.service.AccountService;
import com.fpoly.service.AuthorityService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping("/accounts")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		
		return accountService.findAll();
	}
	
    // Tạo tài khoản mới cho staff
    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // Cập nhật quyền cho staff
    @PostMapping("/update-authority")
    public Authority updateAuthority(@RequestBody Authority authority) {
        return authorityService.updateAuthority(authority);
    }
   
    
}