package com.example.blink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Name is required")
    private String name;
    
    @Email
    private String email;

    @Pattern(regexp = "\\+\\d{1,3}", message = "Invalid country code format")
    private String countryCode;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String number;

    private String preference;

    private String profilePicturePath; // Path to the profile image

    private String password;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return number;
    }

    public void setPhoneNumber(String number) {
        this.number = number;
    }

    public void setPreference(String preference){
        this.preference = preference;
    }

    public String getPreference(){
        return preference;
    }

    public String getProfilePicturePath(){
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath){
        this.profilePicturePath = profilePicturePath;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
