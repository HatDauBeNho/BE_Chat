package com.example.demo.custom.group.response;

import java.time.LocalDateTime;
import java.util.List;

public class CreateGroupResponse {
    private int groupId;
    private String groupName;
    private int adminId;
    private List<Integer> members;
    private LocalDateTime createdAt;

    public CreateGroupResponse(int groupId, String groupName, int adminId, List<Integer> members, LocalDateTime createdAt) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;
        this.members = members;
        this.createdAt = createdAt;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
