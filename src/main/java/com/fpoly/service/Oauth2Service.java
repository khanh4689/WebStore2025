package com.fpoly.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface Oauth2Service {

	void loginFromOAuth2(OAuth2AuthenticationToken oauth2);

}
