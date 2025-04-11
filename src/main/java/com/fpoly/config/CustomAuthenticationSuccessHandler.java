package com.fpoly.config;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean isBlocked = authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_BLOCK"));

        if (isBlocked) {
            request.getSession().invalidate(); // huỷ session
            response.sendRedirect("/login/security/error?blocked=true");
        } else {
            response.sendRedirect("/login/security/success");
        }
    }
}
