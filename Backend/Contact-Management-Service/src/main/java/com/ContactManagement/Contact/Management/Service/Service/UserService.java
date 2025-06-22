package com.ContactManagement.Contact.Management.Service.Service;

import com.ContactManagement.Contact.Management.Service.model.User;

public interface UserService {
    // Register a new user
    String registerUser(User user);

    // Authenticate user by email and password
    boolean loginUser(String email, String password);

    // Optional: Fetch user by email (useful in token generation or profile retrieval)
    User getUserByEmail(String email);
    String loginUserAndGetToken(String email, String rawPassword);
}
