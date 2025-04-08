package com.fpoly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.dao.AccountDAO;
import com.fpoly.dao.AuthorityDAO;
import com.fpoly.entity.Account;
import com.fpoly.entity.Authority;
import com.fpoly.service.AuthorityService;



@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO adao;
	
	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Authority> getAuthoritiesOfAdministrators() {
		List<Account> accounts = adao.getAccounts();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	   @Override
	    public Authority updateAuthority(Authority authority) {
	        return dao.save(authority);
	    }

}
