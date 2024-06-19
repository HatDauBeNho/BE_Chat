package com.example.demo.custom.group.request;

import com.example.demo.entity.dao.User;

import java.util.List;

public class AddMemberToGroupRequest {
    private int groupId;
    private List<Integer> members;

    public AddMemberToGroupRequest(int groupId, List<Integer> members) {
        this.groupId = groupId;
        this.members = members;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }
}
