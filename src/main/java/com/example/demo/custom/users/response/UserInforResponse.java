package com.example.demo.custom.users.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInforResponse {
    private String userName;
    private String fullName;
    private String avatar;


}
