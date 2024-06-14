package com.example.demo.custom.auth.request;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;

public class SignUpRequest {
    @Pattern(regexp = "^[a-zA-Z0-9]{5,50}$")
    private String userName;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-.]).{8,24}$")
    private String password;

    @Pattern(regexp = "^[a-zA-Z]{5,50}$")
    private String fullName;

    public SignUpRequest(String userName, String password, String fullName)
    {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
