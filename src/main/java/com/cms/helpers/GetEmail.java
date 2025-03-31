package com.cms.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class GetEmail {
    public static String getLoggedUserEmail(Authentication authentication) {
        if(authentication instanceof OAuth2AuthenticationToken) {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            String email = user.getAttribute("email");
            return email;
        }
        return authentication.getName();
    }
}
