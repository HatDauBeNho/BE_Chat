package com.example.demo.repository;

import com.example.demo.entity.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChatRepository extends JpaRepository<Group,Integer> {

}
