package com.example.demo.custom.users.response;

public class UserInforResponse {
    private String userName;
    private String fulllName;
    private String avatar;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFulllName() {
        return fulllName;
    }

    public void setFulllName(String fulllName) {
        this.fulllName = fulllName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserInforResponse(String userName, String fulllName, String avatar) {
        this.userName = userName;
        this.fulllName = fulllName;
        this.avatar = avatar;
    }
}
