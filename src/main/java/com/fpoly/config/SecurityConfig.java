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
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(c -> c.disable())
			.cors(c -> c.disable())
			.authorizeHttpRequests(registry -> {
				registry.requestMatchers("/order/**").authenticated();
				registry.requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE");
			
				registry.anyRequest().permitAll();
			});
		
		httpSecurity.formLogin(loginPage -> {
			loginPage.loginPage("/login/sign-in").permitAll();
			loginPage.loginProcessingUrl("/login/security");
			loginPage.defaultSuccessUrl("/login/security/success", false);
			loginPage.failureUrl("/login/security/error");
		});
		
		// Nếu bạn sử dụng OAuth2, bỏ comment phần này
		/*
		httpSecurity.oauth2Login(oauth2 -> {
			oauth2.loginPage("/login/oauth2/authorization/google");
			oauth2.userInfoEndpoint(u -> u.userService(new CustomerOAuth2UserService()));
			oauth2.defaultSuccessUrl("/login/security/success", false);
			oauth2.failureUrl("/login/security/error");
		});
		*/
	
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
	
	// Thêm bean này nếu bạn cần OAuth2, nếu không có thể bỏ qua
	/*
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(ClientRegistration.withRegistrationId("google").build());
	}
	*/
}
