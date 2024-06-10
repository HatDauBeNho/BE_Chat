package com.example.demo.controller;

import com.example.demo.entity.dao.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers()
    {
        return userService.findAll();
    }

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user)
    {
        return userService.save(user);
    }
}
