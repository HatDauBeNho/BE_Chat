package com.example.demo.custom.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SignInResponse {
    @JsonProperty(value = "token")
    private String token;
    @JsonProperty(value = "userName")
    private String userName;
    @JsonProperty(value = "fullName")
    private String fullName;
    @JsonProperty(value = "avatar")
    private String avatar;

    public SignInResponse(String token, String userName, String fullName, String avatar) {
        this.token = token;
        this.userName = userName;
        this.fullName = fullName;
        this.avatar = avatar;
    }
}
