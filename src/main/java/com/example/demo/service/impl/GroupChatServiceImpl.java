package com.example.demo.service.impl;

import com.example.demo.custom.auth.response.CustomResponse;
import com.example.demo.custom.group.handle.UserNotInGroupHandle;
import com.example.demo.custom.group.request.CreateGroupRequest;
import com.example.demo.entity.dao.Group;
import com.example.demo.entity.dao.GroupMember;
import com.example.demo.entity.dao.User;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.repository.GroupMemberRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service

public class GroupChatServiceImpl implements GroupChatService {
    @Autowired
    GroupChatRepository groupChatRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupMemberRepository groupMemberRepository;

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

    @Override
    public Group saveGroupAndGroupMember(int userId, CreateGroupRequest createGroupRequest) {
        LocalDateTime time = LocalDateTime.now();
        Group group=new Group();
        group.setGroupName(createGroupRequest.getGroupName());
        group.setAdmin(userRepository.findById(userId).get());
        group.setCreatedAt(time);

        groupChatRepository.save(group);

        GroupMember admin=new GroupMember();
        admin.setGroup(group);
        admin.setUser(userRepository.findById(userId).get());
        admin.setCreatedAt(time);
        groupMemberRepository.save(admin);
        for (Integer item:createGroupRequest.getMembers())
        {
            Optional<User> friendOptional= userRepository.findById(item);
            if (friendOptional.isPresent()) {
                GroupMember groupMember=new GroupMember();
                groupMember.setGroup(group);
                groupMember.setUser(friendOptional.get());
                groupMember.setCreatedAt(time);
                groupMemberRepository.save(groupMember);
            }
        }
        return group;
    }

}
