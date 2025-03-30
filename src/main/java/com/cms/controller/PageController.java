package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.cms.entities.User;
import com.cms.forms.LoginForm;
import com.cms.forms.UserForm;
import com.cms.helpers.Message;
import com.cms.helpers.MessageType;
import com.cms.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }    

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
    public String displayLoginPage(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping("/authenticate")
    public String processAuthentication(@ModelAttribute LoginForm loginForm) {        
        return "login";
    }

    @RequestMapping("/signup")
    public String displaySignupPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    // processing register
    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        // validation
        if(rBindingResult.hasErrors()) {
            System.out.println("Error: " + rBindingResult);
            return "signup";
        }
        // fetch data
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setContactNumber(userForm.getContactNo());
        user.setAbout(userForm.getAbout());
        User saveUser = userService.saveUser(user);

        // set message
        Message message = new Message();        
        message.setContent("Registration successfully!");
        message.setType(MessageType.green);

        session.setAttribute("message", message);
        return "redirect:/signup";
    }


}
