package com.example.demo.repository;

import com.example.demo.entity.dao.Group;
import com.example.demo.entity.dao.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember,Integer> {
//    @Query(value = "SELECT * FROM groups  ORDER BY createdAt DESC LIMIT 1",nativeQuery = true)
//    Optional<GroupMember> findByGroup
    @Query(value = "SELECT userId FROM groupMembers WHERE groupId=?1",nativeQuery = true)
    List<Integer> findUserIdInOneGroup(int groupId);

    @Transactional
    @Modifying
    @Query (value = "DELETE FROM groupMembers WHERE groupId=?1 and userId=?2",nativeQuery = true)
    void deleteGroupMember(int groupId,int userId);
}
