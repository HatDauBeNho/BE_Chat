package com.example.demo.entity.dao;


import lombok.Data;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "`groups`")
@EntityListeners(AuditingEntityListener.class)
@Data
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


}
