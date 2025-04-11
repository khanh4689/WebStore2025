package com.fpoly.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.fpoly.dao.AccountDAO;
import com.fpoly.entity.Account;
import com.fpoly.service.Oauth2Service;

@Service
public class Oauth2ServiceImpl implements Oauth2Service, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountDAO.findById(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        Account account = optional.get();
        return User.withUsername(account.getUsername())
                   .password(account.getPassword())
                   .roles("GUEST") // Có thể tùy chỉnh theo Authority thực tế
                   .build();
    }

    @Override
    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
        String email = oauth2.getPrincipal().getAttribute("email");
        String username = email.split("@")[0];

        Account account = accountDAO.findById(username).orElse(null);
        if (account == null) {
            // Tạo tài khoản mới nếu chưa có
            account = new Account();
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode("oauth2_login")); // mật khẩu tạm
            account.setFullname(username);
            account.setEmail(email);
            account.setPhoto("user.png");
            accountDAO.save(account);
        }

        UserDetails user = User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("GUEST") // Có thể lấy từ authorities thực tế nếu cần
                .build();

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
