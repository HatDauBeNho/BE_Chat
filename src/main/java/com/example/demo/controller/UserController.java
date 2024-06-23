package com.example.demo.controller;

import com.example.demo.custom.auth.request.SignUpRequest;
import com.example.demo.custom.auth.response.CustomResponse;
import com.example.demo.custom.users.request.UpdateUserRequest;
import com.example.demo.custom.users.response.UserInforResponse;
import com.example.demo.entity.dao.User;

import com.example.demo.security.service.UserDetailsImpl;

import com.example.demo.service.FileStorageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/updateUser")
    @Transactional
    public ResponseEntity<?> updateUser(@Valid @ModelAttribute UpdateUserRequest updateUserRequest,Errors errors)
    {
        try
        {
            if (errors.hasErrors()) {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Incorrect email format"));
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Optional<User> userOptional = userService.findUserByEmail(updateUserRequest.getEmail());
            if (userOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Email already exists"));
            }

            if (userService.updateUser(
                    userDetails.getUserID(),
                    updateUserRequest.getFullName(),
                    updateUserRequest.getAvatar(),
                    updateUserRequest.getEmail())) return ResponseEntity.ok().body(new CustomResponse<>(1, null, "Success update"));

            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"Update failed"));
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }

    }
    @GetMapping("/infor")
    public ResponseEntity<?> getUserInfor() {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Optional<User> userOptional = userService.findUserByName(userDetails.getUsername());
            if (userOptional.isPresent())
            {
                return ResponseEntity.ok()
                        .body(new CustomResponse<>(
                                1,
                                new UserInforResponse(userDetails.getUsername(), userOptional.get().getFullName(), userOptional.get().getAvatar()),
                                "Get user infor success")
                        );
            }
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Get user failed"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }

    }
}
