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
}
