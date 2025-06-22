package com.ContactManagement.Contact.Management.Service.repository;

import com.ContactManagement.Contact.Management.Service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByStatus(int status);

    boolean existsByEmailId(String emailId);
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Contact> findByEmailId(String emailId);
    Optional<Contact> findByPhoneNumber(String phoneNumber);


}
