package com.example.blink.controller;

import com.example.blink.dto.*;
import com.example.blink.model.User;
import com.example.blink.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allow cross-origin requests
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) {
        userService.saveUser(user);  // Save user
        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());  // Return userId in response
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-contact")
    public ResponseEntity<String> updateContact(@RequestBody UserContactRequest request) {
        try {
            userService.updateContactDetails(request.getUserId(), request.getPhoneNumber(), request.getCountryCode());
            return ResponseEntity.ok("Contact updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-number")
    public ResponseEntity<Map<String, String>> updateUserNumber(@RequestBody UserNumberUpdateRequest request) {
        try {
            userService.updateUserNumber(request.getUserId(), request.getPhoneNumber());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Number updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/update-email")
    public ResponseEntity<Map<String, String>> updateUserEmail(@RequestBody UserEmailUpdateRequest request) {
        try {
            userService.updateUserEmail(request.getUserId(), request.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email Updated Successfully.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/update-name")
    public ResponseEntity<Map<String, String>> updateUserName(@RequestBody UserNameUpdateRequest request) {
        try {
            userService.updateUserName(request.getUserId(), request.getName());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Name Updated Successfully.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
