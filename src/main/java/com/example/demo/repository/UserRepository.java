package com.example.demo.repository;

import com.example.demo.custom.users.handle.FriendInfor;
import com.example.demo.entity.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM users WHERE userName = ?1 LIMIT 1", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE email = ?1 LIMIT 1", nativeQuery = true)
    Optional<User> findByEmail(String email);


    @Query(value = "SELECT userId, fullName, avatar FROM users WHERE userId <> ?1", nativeQuery = true)
    List<FriendInfor> listFriend(int id);
}
