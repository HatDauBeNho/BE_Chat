package com.example.demo.custom.auth.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
@Data
public class SignUpRequest {
    @Pattern(regexp = "^[a-zA-Z0-9]{5,50}$")
    private String userName;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-.]).{8,24}$")
    private String password;

    @Pattern(regexp = "^[a-zA-Z ]{5,50}$")
    private String fullName;



}
