package com.ContactManagement.Contact.Management.Service.controller;

import com.ContactManagement.Contact.Management.Service.Service.ContactService;
import com.ContactManagement.Contact.Management.Service.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Get all contacts (status = 0)
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAll());
    }

    // Get contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    // Create new contact
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.CreateContact(contact));
    }

    // Update contact
    @PutMapping
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.updateContact(contact));
    }

    // Soft delete contact (status = 1)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id); // implement by setting status = 1
        return ResponseEntity.ok("Contact deleted (soft delete)");
    }
}
