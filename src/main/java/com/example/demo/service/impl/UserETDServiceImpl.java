package com.example.demo.service.impl;

import com.example.demo.etd.UserETD;
import com.example.demo.repository.UserETDRepository;
import com.example.demo.service.UserETDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserETDServiceImpl implements UserETDService {
    @Autowired
    private UserETDRepository userETDRepository;

    @Override
    public List<UserETD> findAll() {
        return (List<UserETD>)userETDRepository.findAll();
    }

    @Override
    public Optional<UserETD> findById(Integer id) {
        return userETDRepository.findById(id);
    }

    @Override
    public UserETD save(UserETD userETD) {
        return userETDRepository.save(userETD);
    }

    @Override
    public void deleteById(Integer id) {
        userETDRepository.deleteById(id);
    }
}
