package com.example.demo.custom.users.response;

public class UserInforResponse {
    private String userName;
    private String fullName;
    private String avatar;

    public UserInforResponse(String userName, String fullName, String avatar) {
        this.userName = userName;
        this.fullName = fullName;
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
