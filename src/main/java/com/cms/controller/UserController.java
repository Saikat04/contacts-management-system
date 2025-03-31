package com.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.helpers.GetEmail;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    // user dashboard page
    @RequestMapping("/dashboard")
    public String userDashboard(Authentication authentication) {
        String userEmail = GetEmail.getLoggedUserEmail(authentication);
        logger.info(userEmail);
        return "user/dashboard";
    }
    // user profile page
    @RequestMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
}
