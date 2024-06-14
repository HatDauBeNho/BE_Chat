package com.example.demo.custom.users.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Parent;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;

public class UpdateUserRequest {
    @Pattern(regexp = "^[\\p{L} '-]+$")
    private String fullName;

    private MultipartFile avatar;

    @Pattern(regexp = ".*@gmail\\.com$")
    private String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UpdateUserRequest(String fullName, MultipartFile avatar, String email) {
        this.fullName = fullName;
        this.avatar = avatar;
        this.email = email;
    }
}
