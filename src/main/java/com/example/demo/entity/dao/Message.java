package com.example.demo.entity.dao;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table (name = "messages")

@EntityListeners(AuditingEntityListener.class)

public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id",nullable = false)
    private int messageID;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "from_user_id",referencedColumnName = "user_id")
    private User fromUser;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "to_user_id",referencedColumnName = "user_id")
    private User toUser;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "to_group_id",referencedColumnName = "group_id")
    private Group toGroup;

    @Column(name = "content")
    private String content;

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Group getToGroup() {
        return toGroup;
    }

    public void setToGroup(Group toGroup) {
        this.toGroup = toGroup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
