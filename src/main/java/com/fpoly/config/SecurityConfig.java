package com.fpoly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fpoly.service.MyUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	MyUserDetailService myUserDetailService;
	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(c -> c.disable())
			.cors(c -> c.disable())
			.authorizeHttpRequests(registry -> {
				registry.requestMatchers("/order/**").authenticated();
				registry.requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE");
				registry.requestMatchers("/rest/authorities").hasRole("DIRE");
				
				registry.anyRequest().permitAll();
			});

		httpSecurity.formLogin(loginPage -> {
		    loginPage.loginPage("/login/sign-in").permitAll();
		    loginPage.loginProcessingUrl("/login/security");
		    loginPage.successHandler(customAuthenticationSuccessHandler);
	        loginPage.failureHandler(customAuthenticationFailureHandler); 
		    loginPage.failureUrl("/login/security/error");
		
		    
		});


		httpSecurity.oauth2Login()
			.loginPage("/auth/login/form")
			.defaultSuccessUrl("/auth2/login/success", true)
			.failureUrl("/auth/login/error")
			.authorizationEndpoint()
				.baseUri("/oauth2/authorization");

		httpSecurity.rememberMe(re -> {
			re.userDetailsService(userDetailsService());
			re.tokenValiditySeconds(86400);
		});

		httpSecurity.logout(lo -> {
			lo.logoutUrl("/login/security/out");
			lo.logoutSuccessUrl("/login/security/out/success");
		});

		return httpSecurity.build();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	WebSecurityCustomizer customizer() {
		return web -> web.ignoring().requestMatchers("/static/**");
	}
	
	
}
