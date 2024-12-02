package com.example.blink.dto;

public class UserPreferenceRequest {
    private Long userId;
    private String preference;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
}
