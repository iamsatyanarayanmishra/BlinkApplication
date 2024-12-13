package com.example.blink.controller;

import com.example.blink.model.User;
import com.example.blink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private UserService userService;

    // Fetch all users (chats) - You can customize the data to fetch from the User model
    @GetMapping("/{userId}")
    public ResponseEntity<List<User>> getChats(@PathVariable long userId, @RequestParam(name = "search", required = false) String search) {
        List<User> users = search != null ? userService.searchUsers(search) : userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Archive a chat (update user preferences)
    @PostMapping("/{userId}/archive")
    public ResponseEntity<User> archiveChat(@PathVariable long userId) {
        User updatedUser = userService.archiveChat(userId);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a chat (remove user)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteChat(@PathVariable long userId) {
        userService.deleteChat(userId);
        return ResponseEntity.noContent().build();
    }
}
