package com.cms.services;

import java.util.List;

import com.cms.entities.Contact;
import com.cms.entities.User;

public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    List<Contact> serchByName(String name, User user);
    List<Contact> serchByEmail(String email, User user);
    List<Contact> serchByPhoneNumber(String phoneNumber, User user);
    // List<Contact> getByUserId(String userId);
    List<Contact> getByUser(User user);
}
