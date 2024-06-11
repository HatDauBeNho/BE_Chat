package com.example.demo.entity.dao;

import javax.persistence.*;



import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id",nullable = false)
    private int userID;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="avatar")
    private String avatar;

    @Column(name = "social_id")
    private String socialID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getSocialID() {
        return socialID;
    }

    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }
}
