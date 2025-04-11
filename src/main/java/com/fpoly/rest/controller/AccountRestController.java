package com.fpoly.rest.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.entity.Account;
import com.fpoly.entity.Authority;
import com.fpoly.entity.Role;
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
	@PostMapping("/accounts/create")
	public Account createAccountWithRoles(@RequestBody Account account, @RequestParam List<String> roleIds) {
	    Account acc = accountService.createAccount(account);
	    roleIds.forEach(roleId -> {
	        Authority auth = new Authority();
	        auth.setAccount(acc);
	        auth.setRole(new Role(roleId));
	        authorityService.create(auth);
	    });
	    return acc;
	}


	// Cập nhật quyền cho staff
	@PostMapping("/update-authority")
	public Authority updateAuthority(@RequestBody Authority authority) {
		return authorityService.updateAuthority(authority);
	}

	// Upload ảnh đại diện
	@PostMapping("/upload")
	public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
		try {
			String filename = file.getOriginalFilename();
			File saveFile = new File("uploads/" + filename);
			file.transferTo(saveFile);

			Map<String, String> result = new HashMap<>();
			result.put("name", filename);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
