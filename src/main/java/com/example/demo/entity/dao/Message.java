package com.example.demo.entity.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table (name = "messages")
@Data
@AllArgsConstructor
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
