package com.ContactManagement.Contact.Management.Service.controller;

import com.ContactManagement.Contact.Management.Service.Config.JwtUtils;
import com.ContactManagement.Contact.Management.Service.model.User;
import com.ContactManagement.Contact.Management.Service.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Registration
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid User user) {
        String result = userService.registerUser(user);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User loginRequest) {
        String token = userService.loginUserAndGetToken(loginRequest.getEmailAddress(), loginRequest.getPassword());
        //userService.
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

}
