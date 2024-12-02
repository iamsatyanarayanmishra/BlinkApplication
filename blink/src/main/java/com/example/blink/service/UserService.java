package com.example.blink.service;
import com.example.blink.model.User;
import com.example.blink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save user functionalities
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return userRepository.save(user);
    }

    // Update user contact details
    public void updateContactDetails(Long userId, String number, String countryCode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update user details
        user.setPhoneNumber(number);
        user.setCountryCode(countryCode);

        userRepository.save(user); // Save the updated user
    }

    // Find user by ID
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getUserDetails(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);  // Return null if user not found
    }

    public void updateUserNumber(Long userId, String number) {
    
        // Fetch user or throw an exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    
        // Update user details
        user.setPhoneNumber(number);
    
        // Save updated user entity
        userRepository.save(user);
    }

    public void updateUserEmail(Long userId, String email){
        // Fetch user or throw an exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setEmail(email);
        userRepository.save(user);
    }

    public void updateUserName(Long userId, String name){
        // Fetch user or throw an exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setName(name);
        userRepository.save(user);
    }
}
