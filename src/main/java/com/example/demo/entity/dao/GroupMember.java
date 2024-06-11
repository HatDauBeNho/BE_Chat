package com.example.demo.entity.dao;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;



@Entity
@Table(name = "group_members")

@EntityListeners(AuditingEntityListener.class)
public class GroupMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_member_id",nullable = false)
    private int groupMemberID;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public int getGroupMemberID() {
        return groupMemberID;
    }

    public void setGroupMemberID(int groupMemberID) {
        this.groupMemberID = groupMemberID;
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
