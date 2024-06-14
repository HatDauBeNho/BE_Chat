package com.example.demo.service.impl;

import com.example.demo.entity.dao.Group;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupChatServiceImpl implements GroupChatService {
    @Autowired
    GroupChatRepository groupChatRepository;
    @Override
    public Group createGroup(Group group) {
        return groupChatRepository.save(group);
    }
}
