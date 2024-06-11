package com.example.demo.controller;

import com.example.demo.entity.dao.User;
import com.example.demo.entity.dto.CustomResponse;
import com.example.demo.entity.dto.SignInResponse;
import com.example.demo.entity.dto.SignUpDto;

import com.example.demo.entity.dto.UpdateUserRequest;
import com.example.demo.security.service.UserDetailsImpl;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public List<User> getAllUsers()
    {
        return userService.findAll();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        Optional<User> userOptional = userService.findUserByName(signUpDto.getUserName());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "User already exists"));
        }

        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        User user = new User();
        user.setFullName(signUpDto.getFullName());
        user.setUserName(signUpDto.getUserName());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setCreatedAt(time);

        userService.save(user);

        return  ResponseEntity.ok().body(new CustomResponse<>(1, null, "Success register"));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest)
    {
        Optional<User> userOptional = userService.findUserByEmail(updateUserRequest.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Email already exists"));
        }
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        userService.updateUser(userOptional.get().getUserID(),updateUserRequest.getEmail(),updateUserRequest.getAvatar(),time);
        return  ResponseEntity.ok().body(new CustomResponse<>(1, null, "Success register"));
    }
    @GetMapping("/getUserInfor")
    public ResponseEntity<?> getUserInfor()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> userOptional = userService.findUserByName(userDetails.getUsername());

        return  ResponseEntity.ok()
                .body(new CustomResponse<>(
                        1,
                        new SignInResponse(userDetails.getUsername(),userOptional.get().getFullName(),userOptional.get().getAvatar()),
                        "Success Login")
                );

    }
}
