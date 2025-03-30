package com.cms.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.cms.entities.Provider;
import com.cms.entities.User;
import com.cms.helpers.AppConstants;
import com.cms.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                logger.info("OAuthAuthenticationSuccessHandler");
                DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
                String email = user.getAttribute("email");
                String name = user.getAttribute("name");
                String profileLink = user.getAttribute("profile_link");
                String contactNumber = user.getAttribute("contact_number");
                String about = user.getAttribute("about");

                User userEntity = new User();
                userEntity.setUserId(UUID.randomUUID().toString());
                userEntity.setEmail(email);
                userEntity.setName(name);
                userEntity.setProfileLink(profileLink);
                userEntity.setContactNumber(contactNumber);
                userEntity.setAbout(about);
                userEntity.setEnabled(true);
                userEntity.setEmailVarified(true);
                userEntity.setPhoneVarified(true);
                userEntity.setProviderUserId(user.getName());
                userEntity.setProvider(Provider.GOOGLE);
                userEntity.setPassword("password"); // Set a default password or handle it as per your requirement
                userEntity.setRoleList(List.of(AppConstants.ROLE_USER));

                User existingUser = userRepo.findByEmail(email).orElse(null);
                if (existingUser == null) {
                    userRepo.save(userEntity);
                } else {
                    existingUser.setEnabled(true);
                    existingUser.setEmailVarified(true);
                    existingUser.setPhoneVarified(true);
                    existingUser.setProviderUserId(user.getName());
                    existingUser.setProvider(Provider.GOOGLE);
                    userRepo.save(existingUser);
                }
                
                // Redirect to the desired URL after successful authentication
                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
