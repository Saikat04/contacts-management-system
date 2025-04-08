package com.cms.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entities.Contact;
import com.cms.entities.User;
import com.cms.repositories.ContactRepo;
import com.cms.services.ContactService;
import com.cms.services.ResourceNotFoundException;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Contact not found"));
    }

    @Override
    public void delete(String id) {
        Contact contact = contactRepo.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Contact not found"));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> serch(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serch'");
    }

    @Override
    public List<Contact> getByUser(User user) {
        return contactRepo.findByUser(user);
    }

    // @Override
    // public List<Contact> getByUserId(String userId) {
    //     // return contactRepo.findByUserId(userId);
    // }

}
