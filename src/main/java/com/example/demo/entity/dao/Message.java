package com.example.demo.entity.dao;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table (name = "messages")
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId",nullable = false)
    private int messageID;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "fromUserId",referencedColumnName = "userId")
    private User fromUserID;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "toUserId",referencedColumnName = "userId")
    private User toUserID;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "toGroupId",referencedColumnName = "groupId")
    private Group toGroupID;

    @Column(name = "content")
    private String content;

    public Message(int messageID, User fromUserID, User toUserID, Group toGroupID, String content) {
        this.messageID = messageID;
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.toGroupID = toGroupID;
        this.content = content;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public User getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(User fromUserID) {
        this.fromUserID = fromUserID;
    }

    public User getToUserID() {
        return toUserID;
    }

    public void setToUserID(User toUserID) {
        this.toUserID = toUserID;
    }

    public Group getToGroupID() {
        return toGroupID;
    }

    public void setToGroupID(Group toGroupID) {
        this.toGroupID = toGroupID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
