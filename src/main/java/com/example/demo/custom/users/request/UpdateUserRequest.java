package com.example.demo.custom.users.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Parent;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
@Data
public class UpdateUserRequest {
    @Pattern(regexp = "^[\\p{L} '-]+$")
    private String fullName;

    private MultipartFile avatar;

    @Pattern(regexp = ".*@gmail\\.com$")
    private String email;

}
