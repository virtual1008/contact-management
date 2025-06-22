package com.ContactManagement.Contact.Management.Service.Service;

import com.ContactManagement.Contact.Management.Service.model.Contact;

import java.util.List;

public interface ContactService {
    //get All Contacts
    public List<Contact> getAll();

    //Create Contact
    public Contact CreateContact(Contact contact);

    //get Contact By id
    public Contact getContact(Long id);

    //Update Contact By id
    public Contact updateContact(Contact contact);

    public void deleteContact(Long id);

}
