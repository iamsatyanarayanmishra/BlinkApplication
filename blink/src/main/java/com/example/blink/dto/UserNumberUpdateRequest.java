package com.example.blink.dto;

public class UserNumberUpdateRequest {
    private Long userId;
    private String phoneNumber; // Update this field name to match the frontend

    // Getters and setters
    public Long getUserId(){
        return userId;
    }

    public void setUerId(Long userId){
        this.userId = userId;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
