package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {

}
