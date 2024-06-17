package com.example.demo.custom.group.request;

import com.example.demo.entity.dao.User;

import java.util.List;

public class CreateGroupRequest {
    private String groupName;
    private List<Integer> members;

    public CreateGroupRequest(String groupName, List<Integer> members) {
        this.groupName = groupName;
        this.members = members;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }
}
