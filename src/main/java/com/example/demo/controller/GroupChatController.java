package com.example.demo.controller;

import com.example.demo.custom.auth.request.SignUpRequest;
import com.example.demo.custom.auth.response.CustomResponse;
import com.example.demo.custom.group.request.CreateGroupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/message/group")
public class GroupChatController {
    @PostMapping("")
    @Transactional
    public ResponseEntity<?> createGroupChat(@RequestBody CreateGroupRequest createGroupRequest)
    {
        try
        {

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, e.getMessage(), null));
        }
    }
}
