package com.example.demo.repository;

import com.example.demo.entity.dao.User;
import com.example.demo.entity.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = :username")
    Optional<User> findByUsername(String username);
}
