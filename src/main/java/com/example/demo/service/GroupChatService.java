package com.example.demo.service;

import com.example.demo.custom.group.response.UserNotInGroupReponse;
import com.example.demo.custom.users.response.UserInforResponse;
import com.example.demo.entity.dao.Group;

import java.util.List;
import java.util.Optional;

public interface GroupChatService {
    Group createGroup(Group group);

    Optional<Group> findByGroupName(String groupName);

    Optional<Group> lastGroup();

    List<UserNotInGroupReponse> getListFriendNotInGroup(int groupId);

}
