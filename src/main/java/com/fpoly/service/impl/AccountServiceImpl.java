package com.fpoly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.dao.AccountDAO;
import com.fpoly.entity.Account;
import com.fpoly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO dao;

	@Override
	public Optional<Account> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Account> getAdministrators() {

		return dao.getAccounts();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account create(Account acc) {
		return dao.save(acc);
	}

	@Override
	public void update(Account userObj) {
		dao.save(userObj);

	}

	@Override
	public Optional<Account> findByEmail(String email) {

		return dao.findByEmail(email);
	}

	// Lấy tất cả tài khoản
	public List<Account> getAllAccounts() {
		return dao.findAll();
	}

	// Tạo tài khoản mới
	public Account createAccount(Account account) {
		return dao.save(account);
	}


		


	

}
