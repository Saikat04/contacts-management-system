package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cms.entities.User;
import com.cms.helpers.GetEmail;
import com.cms.services.UserService;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserService userService;
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if(authentication == null) return;
        String userEmail = GetEmail.getLoggedUserEmail(authentication);
        User user = userService.getUserByEmail(userEmail);
        System.out.println(user.getName());
        model.addAttribute("loggedInUser", user);
    }
}
