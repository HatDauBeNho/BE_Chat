package com.example.demo.entity.dao;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;



@Entity
@Table(name = "groupMembers")

@EntityListeners(AuditingEntityListener.class)
public class GroupMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupMemberId",nullable = false)
    private int groupMemberId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId",nullable = false)
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    public int getGroupMemberID() {
        return groupMemberId;
    }

    public void setGroupMemberID(int groupMemberID) {
        this.groupMemberId = groupMemberID;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
