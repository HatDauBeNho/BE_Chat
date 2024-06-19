package com.example.demo.repository;

import com.example.demo.custom.group.handle.GroupInforHandle;
import com.example.demo.custom.group.handle.UserNotInGroupHandle;
import com.example.demo.entity.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupChatRepository extends JpaRepository<Group,Integer> {
    @Query(value = "SELECT * FROM `groups` WHERE groupName=?1 LIMIT 1", nativeQuery = true)
    Optional<Group> findByGroupName(String groupName);

    @Query(value = "SELECT * FROM `groups`  ORDER BY createdAt DESC LIMIT 1",nativeQuery = true)
    Optional<Group> lastGroup();

    @Query(value = "SELECT g.groupId, g.groupName " +
            "FROM `groups` g LEFT OUTER JOIN groupMembers m " +
            "ON g.groupId = m.groupId " +
            "WHERE m.userId = ?1", nativeQuery = true)
    List<GroupInforHandle> listGroup(int userId);

    @Query(value = "SELECT u.userId, u.fullName, u.avatar " +
            "FROM users u " +
            "WHERE u.userId " +
            "NOT IN (SELECT gm.userId  FROM groupMembers gm WHERE gm.groupId = ?1)",nativeQuery = true)
    List<UserNotInGroupHandle> getListFriendNotInGroup(int groupId);

}
