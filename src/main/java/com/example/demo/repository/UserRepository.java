package com.example.demo.repository;

import com.example.demo.entity.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = :username")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("UPDATE user u set u.emai:=email,u.avatar:=avatar,u.updatedAt:=time, where u.userID:=userid ")
    void updateUser(int userid, String email, String avatar, LocalDateTime time);
}
