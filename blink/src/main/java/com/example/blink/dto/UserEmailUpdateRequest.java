package com.example.blink.dto;

import jakarta.validation.constraints.Email;

public class UserEmailUpdateRequest {
    private Long userId;
    @Email
    private String email;;

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
