package com.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contact")
public class ContactController {
    //add contact page : handler method
    @RequestMapping("/add")
    public String addContact() {
        return "contacts/add_contact_form";
    }

}
