package com.example.demo.custom.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse<T> {
    private int status;
    private T data;
    private String message;


}
