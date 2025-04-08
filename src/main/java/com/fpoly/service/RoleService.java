package com.fpoly.service;

import java.util.List;

import com.fpoly.entity.Role;



public interface RoleService {

	List<Role> getAll();
	
	Role findById(String id);

}

