package com.example.blink.dto;

public class UserNameUpdateRequest {
    private Long userId;
    private String name;

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}