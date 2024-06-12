package com.example.demo.custom.users.request;

public class UpdateUserRequest {
    private String email;
    private String avatar;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UpdateUserRequest(String email, String avatar) {
        this.email = email;
        this.avatar = avatar;
    }
}
