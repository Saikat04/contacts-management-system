package com.cms.controller;

import java.util.List;

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
import com.cms.services.ImageService;
import com.cms.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/user/contact")
public class ContactController {
    @Autowired
    private ImageService imageService;
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
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Validation error: " + error.getDefaultMessage());
            });
    
            // You can also access field-specific errors
            bindingResult.getFieldErrors().forEach(error -> {
                System.out.println("Field: " + error.getField());
                System.out.println("Rejected value: " + error.getRejectedValue());
                System.out.println("Message: " + error.getDefaultMessage());
            });
    
            Message message = new Message();        
            message.setContent("Please, correct the following errors...");
            message.setType(MessageType.red);
            session.setAttribute("message", message);
            return "contacts/add_contact_form";
        }
        String userName = GetEmail.getLoggedUserEmail(authentication);
        User user = userService.getUserByEmail(userName);
        // process image
        String url = "https://res.cloudinary.com/dyo7nfx15/image/upload/v1744133647/contactsdefaultimage_p9rj2a.png";
        if(contactForm.getContactPhoto() != null && !contactForm.getContactPhoto().isEmpty()) {
            url = imageService.uploadImage(contactForm.getContactPhoto());
        }
        
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setWebsiteLink(contactForm.getWURL());
        contact.setSocialLink(contactForm.getSURL());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setPicture(url);
        contact.setUser(user);
        contactService.save(contact);
        System.out.println("User saved successfully");
        Message message = new Message();        
        message.setContent("Contact added successfully!!!");
        message.setType(MessageType.green);
        session.setAttribute("message", message);
        return "redirect:/user/contact/add";
    }
    
    @RequestMapping("/allContacts")
    public String showAllContacts(Model model, Authentication authentication) {
        String userName = GetEmail.getLoggedUserEmail(authentication);
        User user = userService.getUserByEmail(userName);
        List<Contact> contacts = contactService.getByUser(user);
        model.addAttribute("contacts", contacts);
        return "contacts/allContacts";
    }

    @RequestMapping("/search")
    public String searchHandler(
        @RequestParam("field") String field,
        @RequestParam("keyword") String value, Model model,
        Authentication authentication) {
            var user=userService.getUserByEmail(GetEmail.getLoggedUserEmail(authentication));
            
            List<Contact> matchedContacts = null;
            if(field.equalsIgnoreCase("name")) {
                matchedContacts = contactService.serchByName(value, user);
            } else if(field.equalsIgnoreCase("email")) {
                matchedContacts = contactService.serchByEmail(value, user);
            } else if(field.equalsIgnoreCase("number")){
                matchedContacts = contactService.serchByPhoneNumber(value, user);
            }
            model.addAttribute("matchedContact", matchedContacts);

            return "contacts/searchContact";
    }
    

}
