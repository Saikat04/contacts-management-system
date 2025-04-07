package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.entities.Contact;
import com.cms.entities.User;
import com.cms.forms.ContactForm;
import com.cms.helpers.GetEmail;
import com.cms.helpers.Message;
import com.cms.helpers.MessageType;
import com.cms.services.ContactService;
import com.cms.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    //add contact page : handler method
    @RequestMapping("/add")
    public String addContact(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "contacts/add_contact_form";
    }
    @PostMapping("/addNew")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Authentication authentication, HttpSession session) {
        if(bindingResult.hasErrors()) {
            Message message = new Message();        
            message.setContent("Please, correct the following errors...");
            message.setType(MessageType.red);
            session.setAttribute("message", message);
            return "contacts/add_contact_form";
        }
        String userName = GetEmail.getLoggedUserEmail(authentication);
        User user = userService.getUserByEmail(userName);
        
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setWebsiteLink(contactForm.getWURL());
        contact.setSocialLink(contactForm.getSURL());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setUser(user);
        contactService.save(contact);
        System.out.println("User saved successfully");
        Message message = new Message();        
        message.setContent("Contact added successfully!!!");
        message.setType(MessageType.green);
        session.setAttribute("message", message);
        return "redirect:/user/contact/add";
    }
    

}
