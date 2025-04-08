package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.entity.Account;

public interface AccountService {
	Optional<Account> findByUsername(String username);

	List<Account> getAdministrators();

	List<Account> findAll();
	
	Account create(Account acc);

	void update(Account userObj);

	Optional<Account> findByEmail(String email);

	Account createAccount(Account account);

	

	
	
}
