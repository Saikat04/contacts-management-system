package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.cms.entities.User;
import com.cms.forms.UserForm;
import com.cms.services.UserService;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public void displayHomePage() {
        System.out.println("Displaying Home Page");
    }

    @RequestMapping("/about")
    public void displayAboutPage() {
        System.out.println("Displaying About Page");
    }

    @RequestMapping("/services")
    public void displayServicesPage() {
        System.out.println("Displaying Services Page");
    }
    
    @RequestMapping("/contact")
    public void displayContactPage() {
        System.out.println("Displaying Services Page");
    }

    @RequestMapping("/login")
    public void displayLoginPage() {
        System.out.println("Displaying Login Page");
    }

    @RequestMapping("/signup")
    public String displaySignupPage(Model model) {
        System.out.println("Displaying Signup Page");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    // processing register
    @PostMapping("/register")
    public String processRegister(@ModelAttribute UserForm userForm) {
        // fetch data
        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .contactNumber(userForm.getContactNo())
                .build();
        User saveUser = userService.saveUser(user);
        System.out.println("User registered successfully: " + user.toString());
        return "redirect:/signup";
    }


}
