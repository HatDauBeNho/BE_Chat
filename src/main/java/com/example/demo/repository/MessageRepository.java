package com.example.demo.repository;

import com.example.demo.custom.message.handle.MessageHandle;
import com.example.demo.entity.dao.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    @Query (value = "SELECT * FROM messages " +
            "WHERE (fromUserId = ?1 AND toUserId = ?2) " +
            "OR (fromUserId = ?2 AND toUserId = ?1) " +
            "ORDER BY createdAt DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<MessageHandle> lastMessage(int id1, int id2);
}
