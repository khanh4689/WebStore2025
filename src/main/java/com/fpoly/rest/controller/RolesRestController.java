package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.entity.Role;
import com.fpoly.service.RoleService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RolesRestController {
	@Autowired
	RoleService roleService;
	
	@GetMapping
	public List<Role> getAll(){
		return roleService.getAll();
	}
}
