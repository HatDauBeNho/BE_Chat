package com.example.demo.service.impl;

import com.example.demo.custom.group.handle.UserNotInGroupHandle;
import com.example.demo.entity.dao.Group;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class GroupChatServiceImpl implements GroupChatService {
    @Autowired
    GroupChatRepository groupChatRepository;
    @Override
    public Group createGroup(Group group) {
        return groupChatRepository.save(group);
    }

    @Override
    public Optional<Group> findByGroupName(String groupName) {
        return groupChatRepository.findByGroupName(groupName);
    }

    @Override
    public Optional<Group> lastGroup() {
        return groupChatRepository.lastGroup();
    }

    @Override
    public List<UserNotInGroupHandle> getListFriendNotInGroup(int groupId) {
        return groupChatRepository.getListFriendNotInGroup(groupId);
    }

    @Override
    public Optional<Group> findByGroupId(int groupId) {
        return groupChatRepository.findById(groupId);
    }

}
