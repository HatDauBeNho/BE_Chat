package com.example.demo.custom.users.response;

import java.time.LocalDateTime;

public class FriendResponse {
    private String content;
    private LocalDateTime createdAt;
    private int friendID;
    private String fullName;
    private String userName;
    private String avatar;

    public FriendResponse(String content, LocalDateTime createdAt, int friendID, String fullName, String userName, String avatar) {
        this.content = content;
        this.createdAt = createdAt;
        this.friendID = friendID;
        this.fullName = fullName;
        this.userName = userName;
        this.avatar = avatar;
    }

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

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
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
