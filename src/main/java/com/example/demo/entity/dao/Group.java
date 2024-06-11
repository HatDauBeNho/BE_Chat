package com.example.demo.entity.dao;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "`groups`")

@EntityListeners(AuditingEntityListener.class)

public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id",nullable = false)
    private int groupID;

    @Column(name = "group_name",nullable = false)
    private String groupName;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id",referencedColumnName = "user_id")
    private User admin;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
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
