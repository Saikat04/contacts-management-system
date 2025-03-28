package com.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {
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
    public void displaySignupPage() {
        System.out.println("Displaying Register Page");
    }

    


}
