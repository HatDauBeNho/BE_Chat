package com.example.demo.service.impl;

import com.example.demo.custom.message.handle.MessageHandle;
import com.example.demo.custom.users.handle.FriendInforHandle;
import com.example.demo.entity.dao.User;
import com.example.demo.custom.users.response.FriendResponse;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    FileStorageService fileStorageService;

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean updateUser(int userId, String fullName, MultipartFile avatar, String email) throws IOException
    {
        String uuid = UUID.randomUUID().toString();
        if (fileStorageService.saveAvatar(avatar,uuid))
        {
            String avatarUrl="/api/images/avatar/"+uuid;
            LocalDateTime updatedAt = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            userRepository.updateUser(userId,fullName, avatarUrl, email,updatedAt);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> getUserInfor(int userId) {
        return userRepository.getUserInfor(userId);
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<FriendInforHandle> listFriend(int id) {
        return userRepository.listFriend(id);
    }


}
