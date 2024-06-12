package com.example.demo.controller;
import com.example.demo.entity.dao.User;
import com.example.demo.entity.dto.CustomResponse;
import com.example.demo.entity.dto.SignInDto;
import com.example.demo.entity.dto.SignInResponse;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.service.UserDetailsImpl;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
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
    public ResponseEntity<?> authenticateUser(@RequestBody SignInDto signInDto){
        try
        {
            Optional<User> userOptional = userService.findUserByName(signInDto.getUserName());
            if (userOptional.isEmpty()){
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "login failed!"));
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDto.getUserName(), signInDto.getPassword()));
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
