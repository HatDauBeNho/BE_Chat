package com.example.demo.repository;

import com.example.demo.entity.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GroupChatRepository extends JpaRepository<Group,Integer> {
    @Query(value = "SELECT * FROM `groups` WHERE groupName=?1 LIMIT 1", nativeQuery = true)
    Optional<Group> findByGroupName(String groupName);

    @Query(value = "SELECT * FROM `groups`  ORDER BY createdAt DESC LIMIT 1",nativeQuery = true)
    Optional<Group> lastGroup();

}
