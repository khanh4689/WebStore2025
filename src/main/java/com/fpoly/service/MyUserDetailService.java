package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpoly.entity.Account;

@Service
public class MyUserDetailService implements UserDetailsService {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    @Lazy 
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> user = accountService.findByUsername(username);
        
        if (user.isPresent()) {
            Account userObj = user.get();

            // Không kiểm tra BLOCK tại đây nữa

            // Mã hóa mật khẩu nếu chưa mã hóa
            String storedPassword = userObj.getPassword();
            if (storedPassword == null || !storedPassword.startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(storedPassword);
                userObj.setPassword(encodedPassword);
                accountService.update(userObj);
            }

            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getAuthorities().stream()
                        .map(er -> er.getRole().getId())
                        .toArray(String[]::new))
                    .build();
        } else {
            throw new UsernameNotFoundException("Không tìm thấy " + username);
        }
    }
}
