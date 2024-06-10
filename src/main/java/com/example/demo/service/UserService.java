package com.example.demo.service;

import com.example.demo.entity.dao.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findUserByName(String name){
        return userRepository.findByUsername(name);
    }
    public List<User> findAll()
    {
        return userRepository.findAll();
    }
    public User save(User user){
        return userRepository.save(user);
    }

}
