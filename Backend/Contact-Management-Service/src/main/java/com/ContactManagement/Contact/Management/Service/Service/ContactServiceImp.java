package com.ContactManagement.Contact.Management.Service.Service;

import com.ContactManagement.Contact.Management.Service.Exception.DuplicateResourceException;
import com.ContactManagement.Contact.Management.Service.Exception.ResourceNotFoundException;
import com.ContactManagement.Contact.Management.Service.model.Contact;
import com.ContactManagement.Contact.Management.Service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImp implements ContactService{

    @Autowired
    ContactRepository contactRepo;
    @Override
    public List<Contact> getAll() {
        // Fetch only contacts with status = 0 (active contacts)
        return contactRepo.findByStatus(0);
    }

    /*
    @Override
    public Contact CreateContact(Contact contact) {
        System.out.println(contact.toString());

        if (contactRepo.existsByEmailId(contact.getEmailId())) {
            throw new ResourceNotFoundException("Email already exists");
        }
        if (contactRepo.existsByPhoneNumber(contact.getPhoneNumber())) {
            throw new ResourceNotFoundException("Phone number already exists");
        }
        System.out.println("ends here");
        contact.setStatus(0);
        return contactRepo.save(contact);
    }*/

    @Override
    public Contact CreateContact(Contact contact) {
        /*if (contactRepo.existsByEmailId(contact.getEmailId())) {
            System.out.println("Email already exists");
            //return null;
        }
        if (contactRepo.existsByPhoneNumber(contact.getPhoneNumber())) {
            System.out.println("Phone number already exists");
            //return null;
        }*/
        if (contactRepo.existsByEmailId(contact.getEmailId())) {
            throw new DuplicateResourceException("Email already exists");
        }
        if (contactRepo.existsByPhoneNumber(contact.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }
        contact.setStatus(0);
        return contactRepo.save(contact);
    }


    @Override
    public Contact getContact(Long id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + id));
    }


    /*
    @Override
    public Contact updateContact(Contact contact) {
        Contact existingContact = contactRepo.findById(contact.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + contact.getId()));

        // Check for duplicate email
        Optional<Contact> emailOwner = contactRepo.findByEmailId(contact.getEmailId());
        if (emailOwner.isPresent() && emailOwner.get().getId() != contact.getId()) {
            throw new DuplicateResourceException("Email already exists");
        }

        // Check for duplicate phone number
        Optional<Contact> phoneOwner = contactRepo.findByPhoneNumber(contact.getPhoneNumber());
        if (phoneOwner.isPresent() && phoneOwner.get().getId() != contact.getId()) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        // Update fields
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setMiddleName(contact.getMiddleName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setEmailId(contact.getEmailId());
        existingContact.setDateOfBirth(contact.getDateOfBirth());
        existingContact.setGender(contact.getGender());
        existingContact.setHouseName(contact.getHouseName());
        existingContact.setCity(contact.getCity());
        existingContact.setState(contact.getState());
        existingContact.setCountry(contact.getCountry());
        existingContact.setZipCode(contact.getZipCode());

        return contactRepo.save(existingContact);
    }
 */
    @Override
    public Contact updateContact(Contact contact) {
        Contact existingContact = contactRepo.findById(contact.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + contact.getId()));

        // Check for duplicate email
        Optional<Contact> emailOwner = contactRepo.findByEmailId(contact.getEmailId());
        if (emailOwner.isPresent() && emailOwner.get().getId() != contact.getId()) {
            throw new DuplicateResourceException("Email already exists");
        }

        // Check for duplicate phone number
        Optional<Contact> phoneOwner = contactRepo.findByPhoneNumber(contact.getPhoneNumber());
        if (phoneOwner.isPresent() && phoneOwner.get().getId() != contact.getId()) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        // Update fields
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setMiddleName(contact.getMiddleName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setEmailId(contact.getEmailId());
        existingContact.setDateOfBirth(contact.getDateOfBirth());
        existingContact.setGender(contact.getGender());
        existingContact.setHouseName(contact.getHouseName());
        existingContact.setCity(contact.getCity());
        existingContact.setState(contact.getState());
        existingContact.setCountry(contact.getCountry());
        existingContact.setZipCode(contact.getZipCode());

        return contactRepo.save(existingContact);
    }



    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + id));

        if (contact.getStatus() == 1) {
            throw new ResourceNotFoundException("Contact already deleted or does not exist");
        }

        contact.setStatus(1); // Soft delete
        contactRepo.save(contact);
    }



}
