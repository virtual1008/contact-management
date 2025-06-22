package com.ContactManagement.Contact.Management.Service.Service;

import com.ContactManagement.Contact.Management.Service.Config.JwtUtils;
import com.ContactManagement.Contact.Management.Service.model.User;
import com.ContactManagement.Contact.Management.Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(User user) {
        if (userRepo.existsByEmailAddress(user.getEmailAddress())) {
            return "Email already exists";
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered successfully";
    }


    /*public String loginUserAndGetToken(String email, String rawPassword) {
        User user = userRepo.findByEmailAddress(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT token
        return jwtUtils.generateJwt(user.getEmailAddress());
    }*/
    public String loginUserAndGetToken(String email, String rawPassword) {
        User user = userRepo.findByEmailAddress(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtils.generateJwt(user.getEmailAddress());
    }
    /*
    @Override
    public boolean loginUser(String email, String password) {
        User user = userRepo.findByEmailAddress(email);
        if (user == null) {
            return false; // User not found
        }

        // Compare encoded password with raw input
        return passwordEncoder.matches(password, user.getPassword());
    }
 */
    @Override
    public boolean loginUser(String email, String password) {
        Optional<User> optionalUser = userRepo.findByEmailAddress(email);
        if (optionalUser.isEmpty()) {
            return false; // User not found
        }

        User user = optionalUser.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
    /*
    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmailAddress(email);
    }

     */
    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmailAddress(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

}
