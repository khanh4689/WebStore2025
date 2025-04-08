package com.fpoly.service;

import java.util.List;

import com.fpoly.entity.Authority;



public interface AuthorityService {

	List<Authority> findAll();

	List<Authority> getAuthoritiesOfAdministrators();

	Authority create(Authority auth);

	void delete(Integer id);

	Authority updateAuthority(Authority authority);

}

