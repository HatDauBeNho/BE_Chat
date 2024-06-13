package com.example.demo.controller;

import com.example.demo.auth.request.SignUpRequest;
import com.example.demo.auth.response.CustomResponse;
import com.example.demo.custom.users.request.UpdateUserRequest;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){

        Optional<User> userOptional = userService.findUserByName(signUpRequest.getUserName());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "User already exists"));
        }

        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        User user = new User();
        user.setFullName(signUpRequest.getFullName());
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setCreatedAt(time);

        userService.createUser(user);

        return  ResponseEntity.ok().body(new CustomResponse<>(1, null, "Success register"));
    }
    @GetMapping("/list-friend")
    public ResponseEntity<?> getAllUsers()
    {
        try
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return  ResponseEntity.ok()
                    .body(new CustomResponse<>(
                            1,
                            userService.getListFriendResponse(userDetails.getUserID()),
                            "Success get list friend")
                    );
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }

    }



    @PostMapping("/updateUser")
    @Transactional
    public ResponseEntity<?> updateUser(@ModelAttribute UpdateUserRequest updateUserRequest)
    {
        try
        {
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
//    @GetMapping("/getUserInfor")
//    public ResponseEntity<?> getUserInfor()
//    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Optional<User> userOptional = userService.findUserByName(userDetails.getUsername());
//
//        return  ResponseEntity.ok()
//                .body(new CustomResponse<>(
//                        1,
//                        new SignInResponse(userDetails.getUsername(),userOptional.get().getFullName(),userOptional.get().getAvatar()),
//                        "Success Login")
//                );
//
//    }
}
