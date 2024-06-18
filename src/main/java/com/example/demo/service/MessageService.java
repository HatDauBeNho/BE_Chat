package com.example.demo.service;

import com.example.demo.custom.message.handle.MessageHandle;
import com.example.demo.custom.users.response.FriendResponse;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<MessageHandle> lastFriendMessage(int id1, int id2);
    Optional<MessageHandle> lastGroupMessage(int groupId);

    List<FriendResponse> getListFriendResponse(int userID);

}
