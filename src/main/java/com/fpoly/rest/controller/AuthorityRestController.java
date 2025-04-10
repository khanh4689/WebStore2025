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

import com.fpoly.dao.AccountDAO;
import com.fpoly.dao.AuthorityDAO;
import com.fpoly.dao.RoleDAO;
import com.fpoly.entity.Authority;
import com.fpoly.service.AuthorityService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AuthorityRestController {
	@Autowired
	AuthorityService authorityService;
	@Autowired
	AuthorityDAO authorityDAO;
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	RoleDAO dao;

	@GetMapping("/authorities")
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return authorityService.getAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}
	
	@PostMapping("/authorities")
	public Authority post(@RequestBody Authority auth) {
		return authorityService.create(auth);
	}
	
	@DeleteMapping("/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.delete(id);
	}
}
