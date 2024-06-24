package com.example.demo.service.impl;

import com.example.demo.custom.message.handle.MessageHandle;
import com.example.demo.custom.users.handle.FriendInforHandle;
import com.example.demo.custom.users.request.UpdateUserRequest;
import com.example.demo.entity.dao.Image;
import com.example.demo.entity.dao.User;
import com.example.demo.custom.users.response.FriendResponse;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageService imageService;

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
    public void updateUser(int userId, UpdateUserRequest updateUserRequest) throws IOException {
        User exitsUser=userRepository.findById(userId).get();
        if (updateUserRequest.getFullName()!=null)
            if (!updateUserRequest.getFullName().equals(exitsUser.getFullName()))exitsUser.setFullName(updateUserRequest.getFullName());
        if (updateUserRequest.getEmail()!=null)
            if (!updateUserRequest.getEmail().equals(exitsUser.getEmail())) exitsUser.setEmail(updateUserRequest.getEmail());
        if (updateUserRequest.getAvatar()!=null)
        {
            String uuid = UUID.randomUUID().toString();
            fileStorageService.saveAvatar(updateUserRequest.getAvatar(), uuid);
            exitsUser.setAvatar("/api/images/avatar/" + uuid);
            Image image=new Image();
            image.setImageName(Objects.requireNonNull(updateUserRequest.getAvatar().getOriginalFilename()));
            image.setUrlImage("/api/images/avatar/" + uuid);
            image.setCreatedAt(LocalDateTime.now());
            imageService.saveImage(image);
        }
        exitsUser.setUpdatedAt(LocalDateTime.now());
        userRepository.save(exitsUser);
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
    public Optional<User> findByIdWithAvatar(int id) {
        return userRepository.findByIdWithAvatar(id);
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
