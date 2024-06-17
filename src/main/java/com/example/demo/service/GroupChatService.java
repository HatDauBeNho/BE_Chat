package com.example.demo.service;

import com.example.demo.entity.dao.Group;

import java.util.Optional;

public interface GroupChatService {
    Group createGroup(Group group);
    Optional<Group> findByGroupName(String groupName);
    Optional<Group> lastGroup();

}
