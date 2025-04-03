package com.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.entities.User;
import com.cms.helpers.GetEmail;
import com.cms.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    
    

    // user dashboard page
    @RequestMapping("/dashboard")
    public String userDashboard(Model model, Authentication authentication) {
        
        return "user/dashboard";
    }
    // user profile page
    @RequestMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
}
