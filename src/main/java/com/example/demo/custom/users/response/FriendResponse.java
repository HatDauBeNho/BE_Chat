package com.example.demo.custom.users.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data

public class FriendResponse {
    private String content;
    private LocalDateTime createdAt;
    private int fromUserId;
    private int toUserId;
    private int toGroupId;
    private String fullName;
    private String userName;
    private String groupName;
    private String avatar;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

//    public FriendResponse(String content, LocalDateTime createdAt, int fromUserId, int toUserId, int toGroupId, String fullName, String userName, String avatar) {
//        this.content = content;
//        this.createdAt = createdAt;
//        this.fromUserId = fromUserId;
//        this.toUserId = toUserId;
//        this.toGroupId = toGroupId;
//        this.fullName = fullName;
//        this.userName = userName;
//        this.avatar = avatar;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(int toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
