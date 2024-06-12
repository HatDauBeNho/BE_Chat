package com.example.demo.service;

import com.example.demo.entity.dao.User;
import com.example.demo.custom.users.response.FriendResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User createUser(User user);

    List<FriendResponse> getListFriendResponse(int userID);

    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);
}
