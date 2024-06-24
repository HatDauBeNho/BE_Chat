package com.example.demo.service;

import com.example.demo.custom.users.handle.FriendInforHandle;
import com.example.demo.custom.users.request.UpdateUserRequest;
import com.example.demo.entity.dao.User;
import com.example.demo.custom.users.response.FriendResponse;
import org.hibernate.sql.Update;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface UserService {
    List<User> findAll();

    User createUser(User user);

    List<FriendInforHandle> listFriend(int id);

    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);

    void updateUser(int userId, UpdateUserRequest updateUserRequest) throws IOException;
    Optional<User> getUserInfor(int userId);

    Optional<User> findUserById(int id);

    Optional<User> findByIdWithAvatar(int id);
}
