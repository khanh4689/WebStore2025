package com.fpoly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fpoly.dao.AccountDAO;
import com.fpoly.entity.Account;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountDAO accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm tài khoản trong database
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Lấy danh sách các quyền (roles)
        List<String> roles = account.getAuthorities().stream()
                .map(auth -> auth.getRole().getId()) 
                .collect(Collectors.toList());

        // Tạo UserDetails cho Spring Security
        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(roles.toArray(new String[0])) 
                .build();
    }
}