package com.cms.services;

import java.util.List;

import com.cms.entities.Contact;

public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    List<Contact> serch(String name, String email, String phoneNumber);
    // List<Contact> getByUserId(String userId);
}
