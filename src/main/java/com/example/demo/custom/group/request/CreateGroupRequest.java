package com.example.demo.custom.group.request;

import com.example.demo.entity.dao.User;

import java.util.List;

public class CreateGroupRequest {
    private String groupName;
    private List<User> members;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public CreateGroupRequest(String groupName, List<User> members) {
        this.groupName = groupName;
        this.members = members;
    }
}
