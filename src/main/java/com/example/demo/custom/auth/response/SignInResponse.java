package com.example.demo.custom.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponse {
    @JsonProperty(value = "token")
    private String token;
    @JsonProperty(value = "userName")
    private String userName;
    @JsonProperty(value = "fullName")
    private String fullName;
    @JsonProperty(value = "avatar")
    private String avatar;


}
