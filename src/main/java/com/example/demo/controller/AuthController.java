package com.example.demo.controller;
import com.example.demo.entity.dao.User;
import com.example.demo.auth.response.CustomResponse;
import com.example.demo.auth.request.SignInRequest;
import com.example.demo.auth.response.SignInResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
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

}
