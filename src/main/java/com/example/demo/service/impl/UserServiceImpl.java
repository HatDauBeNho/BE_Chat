package com.example.demo.service.impl;

import com.example.demo.custom.message.handle.MessageHandle;
import com.example.demo.custom.users.handle.FriendInforHandle;
import com.example.demo.entity.dao.User;
import com.example.demo.custom.users.response.FriendResponse;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //    public void updateUser(int id, String email, String avatar, LocalDateTime time)
//    {
//        userRepository.updateUser(id,email,avatar,time);
//    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<FriendResponse> getListFriendResponse(int userID) {
        try {
            List<FriendResponse> list = new ArrayList<>();
            List<FriendInforHandle> listFriendHandle = userRepository.listFriend(userID);
            for (FriendInforHandle item : listFriendHandle) {
                Optional<MessageHandle> lastMess = messageRepository.lastMessage(userID, item.getUserId());
                if (lastMess.isPresent()) {
                    FriendResponse friendResponse = new FriendResponse(
                            lastMess.get().getContent(), lastMess.get().getCreatedAt(), item.getUserId(), item.getFullName(), item.getUserName(), item.getAvatar()
                    );
                    list.add(friendResponse);
                }else {
                    FriendResponse friendResponse = new FriendResponse(
                            null, null, item.getUserId(), item.getFullName(), item.getUserName(), item.getAvatar()
                    );
                    list.add(friendResponse);
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get list friend response. Error: " + e.getMessage());
        }
    }
}
