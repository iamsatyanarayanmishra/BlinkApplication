package com.example.blink.dto;

public class UserContactRequest {
    private Long userId; // Ensure the frontend sends the logged-in user's ID
    private String number;
    private String countryCode;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return number;
    }

    public void setPhoneNumber(String number) {
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
