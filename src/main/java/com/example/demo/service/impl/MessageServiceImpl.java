package com.example.demo.service.impl;

import com.example.demo.custom.group.handle.GroupInforHandle;
import com.example.demo.custom.message.handle.MessageHandle;
import com.example.demo.custom.users.handle.FriendInforHandle;
import com.example.demo.custom.users.response.FriendResponse;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupChatRepository groupChatRepository;

    @Override
    public Optional<MessageHandle> lastFriendMessage(int id1, int id2)
    {
        return  messageRepository.lastFriendMessage(id1,id2);
    }

    @Override
    public Optional<MessageHandle> lastGroupMessage(int groupId) {
        return messageRepository.lastGroupMessage(groupId);
    }

    @Override
    public List<FriendResponse> getListFriendResponse(int userID) {
        try {
            List<FriendResponse> list = new ArrayList<>();
            List<FriendInforHandle> listFriendHandle = userRepository.listFriend(userID);
            List<GroupInforHandle> listGroupHandle= groupChatRepository.listGroup(userID);
            for (FriendInforHandle item : listFriendHandle) {
                Optional<MessageHandle> lastMess = messageRepository.lastFriendMessage(userID, item.getUserId());
                if (lastMess.isPresent()) {
                    FriendResponse friendResponse = new FriendResponse();
                        friendResponse.setContent(lastMess.get().getContent());
                        friendResponse.setCreatedAt( lastMess.get().getCreatedAt());
                        friendResponse.setFromUserId(userID);
                        friendResponse.setToUserId(item.getUserId());
                        friendResponse.setFullName(item.getFullName());
                        friendResponse.setUserName( item.getUserName());
                        friendResponse.setAvatar(item.getAvatar());
                    list.add(friendResponse);
                }else {
                    FriendResponse friendResponse = new FriendResponse();
                        friendResponse.setFromUserId(userID);
                        friendResponse.setToUserId(item.getUserId());
                        friendResponse.setFullName(item.getFullName());
                        friendResponse.setUserName( item.getUserName());
                        friendResponse.setAvatar(item.getAvatar());
                    list.add(friendResponse);
                }
            }
            for (GroupInforHandle item : listGroupHandle)
            {
                Optional<MessageHandle> lastMess = messageRepository.lastGroupMessage(item.getGroupId());
                if (lastMess.isPresent()) {
                    FriendResponse friendResponse = new FriendResponse();
                    friendResponse.setContent(lastMess.get().getContent());
                    friendResponse.setCreatedAt( lastMess.get().getCreatedAt());
                    friendResponse.setFromUserId(userID);
                    friendResponse.setToGroupId(item.getGroupId());
                    friendResponse.setGroupName(item.getGroupName());
                    list.add(friendResponse);
                }else {
                    FriendResponse friendResponse = new FriendResponse();
                    friendResponse.setFromUserId(userID);
                    friendResponse.setToGroupId(item.getGroupId());
                    friendResponse.setGroupName(item.getGroupName());
                    list.add(friendResponse);
                }
            }
                return list;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get list friend response. Error: " + e.getMessage());
        }
    }
}
