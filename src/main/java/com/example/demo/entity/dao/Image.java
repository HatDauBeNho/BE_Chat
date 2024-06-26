package com.example.demo.entity.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imageId",nullable = false)
    private int imageId;

    @Column(name="imageName",nullable = false)
    private String imageName;

    @Column(name = "urlImage",nullable = false)
    private String urlImage;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "messageId")
    private Message message;


}
