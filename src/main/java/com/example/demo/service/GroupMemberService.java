package com.example.demo.service;

import com.example.demo.entity.dao.GroupMember;

import java.util.List;
import java.util.Optional;

public interface GroupMemberService {
    GroupMember createGroupMember(GroupMember groupMember);

    List<Integer> findUserIdInOneGroup(int groupId);

    void deleteGroupMember(int groupId,int userId);
}
