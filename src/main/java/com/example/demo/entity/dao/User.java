package com.example.demo.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
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


}
