package com.example.demo.service.impl;

import com.example.demo.entity.dao.GroupMember;
import com.example.demo.repository.GroupMemberRepository;
import com.example.demo.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Override
    public GroupMember createGroupMember(GroupMember groupMember) {
        return groupMemberRepository.save(groupMember);
    }

    @Override
    public List<Integer> findUserIdInOneGroup(int groupId) {
        return groupMemberRepository.findUserIdInOneGroup(groupId);
    }
}
