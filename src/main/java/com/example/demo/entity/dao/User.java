package com.example.demo.entity.dao;

import javax.persistence.*;




@Entity
@Table(name = "users")
public class User  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId",nullable = false)
    private int userId;

    @Column(name = "fullName",nullable = false)
    private String fullName;

    @Column(name = "userName",nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="avatar")
    private String avatar;

    @Column(name = "socialId")
    private String socialId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }
}
