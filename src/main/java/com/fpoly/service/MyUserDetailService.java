package com.fpoly.service;

import java.util.Optional;
import java.util.stream.Collectors;

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
    @Lazy // Thêm annotation @Lazy vào đây
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> user = accountService.findByUsername(username);
        
        if (user.isPresent()) {
            Account userObj = user.get();
            String storedPassword = userObj.getPassword();

            // Kiểm tra nếu mật khẩu chưa mã hóa thì mã hóa lại
            if (storedPassword == null || !storedPassword.startsWith("$2a$")) {  
                String encodedPassword = passwordEncoder.encode(storedPassword);
                userObj.setPassword(encodedPassword);
                accountService.update(userObj);  
            }

            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())  // Đã đảm bảo mật khẩu mã hóa
                    .roles(userObj.getAuthorities().stream()
                        .map(er -> er.getRole().getId())
                        .toArray(String[]::new))
                    .build();
        } else {
            throw new UsernameNotFoundException("Không tìm thấy " + username);
        }
    }
}
