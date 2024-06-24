package com.example.demo.repository;

import com.example.demo.custom.users.handle.FriendInforHandle;
import com.example.demo.entity.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM users WHERE userName = ?1 LIMIT 1", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE email = ?1 LIMIT 1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT userId, fullName, avatar FROM users WHERE userId <> ?1", nativeQuery = true)
    List<FriendInforHandle> listFriend(int id);

    @Modifying
    @Query(value =
            "UPDATE users SET fullName = ?2, avatar = ?3,email = ?4,updatedAt = ?5 where userId = ?1",
            nativeQuery = true)
    void updateUser(int userId,String fullName, String avatar, String email, LocalDateTime time);

    @Query(value = "GET userName,fullName,avatar FROM users where userId=?1",nativeQuery = true)
    Optional<User> getUserInfor(int userId);

    @Query(value = "SELECT * FROM users WHERE userId = ?1 AND avatar IS NOT NULL LIMIT 1",nativeQuery = true)
    Optional<User> findByIdWithAvatar(int id);
}
