package com.fpoly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.dao.RoleDAO;
import com.fpoly.entity.Role;
import com.fpoly.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDAO dao;
	
	@Override
	public List<Role> getAll() {
		return dao.findAll();
	}

	@Override
	public Role findById(String id) {
		return dao.findById(id).get();
	}

}
