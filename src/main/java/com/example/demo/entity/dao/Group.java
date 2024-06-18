package com.example.demo.entity.dao;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "`groups`")

@EntityListeners(AuditingEntityListener.class)

public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupId",nullable = false)
    private int groupId;

    @Column(name = "groupName",nullable = false)
    private String groupName;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "adminId",referencedColumnName = "userId")
    private User admin;

    public int getGroupID() {
        return groupId;
    }

    public void setGroupID(int groupID) {
        this.groupId = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
