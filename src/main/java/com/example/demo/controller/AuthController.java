package com.example.demo.controller;
import com.example.demo.custom.auth.request.SignUpRequest;
import com.example.demo.entity.dao.User;
import com.example.demo.custom.auth.response.CustomResponse;
import com.example.demo.custom.auth.request.SignInRequest;
import com.example.demo.custom.auth.response.SignInResponse;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.service.UserDetailsImpl;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserService  userService;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    @Transactional
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest){
        try
        {
            Optional<User> userOptional = userService.findUserByName(signInRequest.getUserName());
            if (userOptional.isEmpty()){
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "login failed!"));
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String jwt = jwtUtils.generateJwtToken(authentication);
            return  ResponseEntity.ok()
                    .body(new CustomResponse<>(
                            1,
                            new SignInResponse(jwt,userDetails.getUsername(),userOptional.get().getFullName(),userOptional.get().getAvatar()),
                            "Success Login")
                    );
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "login failed!"));
        }

    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, Errors errors){
        try
        {
            if (errors.hasErrors()) {
                System.out.println(errors.getAllErrors());
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Incorrect password format"));
            }
            if (StringUtils.countOccurrencesOf(signUpRequest.getPassword(), " ") > 0) {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Password not permitted to contain white spaces"));
            }

            if (StringUtils.countOccurrencesOf(signUpRequest.getUserName(), " ") > 0) {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Username not permitted to contain white spaces"));
            }

            if (signUpRequest.getUserName().length() < 5) {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Incorrect username format"));
            }

            Optional<User> userOptional = userService.findUserByName(signUpRequest.getUserName());

            if (userOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "User already exists"));
            }

            LocalDateTime time = LocalDateTime.now();
            User user = new User();
            user.setFullName(signUpRequest.getFullName());
            user.setUserName(signUpRequest.getUserName());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setCreatedAt(time);

            userService.createUser(user);

            return  ResponseEntity.ok().body(new CustomResponse<>(1, null, "Success register"));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, e.getMessage(), null));
        }

    }
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok().body(new CustomResponse<>(1, null, "Logout successful"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Logout failed"));
        }
    }
}
