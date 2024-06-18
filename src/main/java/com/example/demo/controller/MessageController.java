package com.example.demo.controller;

import com.example.demo.custom.auth.response.CustomResponse;
import com.example.demo.security.service.UserDetailsImpl;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message/")
public class MessageController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/listFriend")
    public ResponseEntity<?> getAllUsers()
    {
        try
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return  ResponseEntity.ok()
                    .body(new CustomResponse<>(
                            1,
                             messageService.getListFriendResponse(userDetails.getUserID()),
                            "Success get list friend")
                    );
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }

    }
}
