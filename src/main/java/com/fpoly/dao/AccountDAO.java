package com.fpoly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.Account;



public interface AccountDAO extends JpaRepository<Account, String> {

Optional<Account> findByUsername(String username);
	
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
	List<Account> getAccounts();
	
	
	@Query("SELECT a FROM Account a WHERE a.email LIKE %:email%")
	Optional<Account> findByEmail(String email);


}
