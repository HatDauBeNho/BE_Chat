package com.example.demo.entity.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fileId",nullable = false)
    private int fileID;

    @Column(name="fileName",nullable = false)
    private String fileName;

    @Column(name = "urlFile",nullable = false)
    private String urlFile;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "messageId")
    private Message message;
}
