package com.example.demo.service.impl;

import com.example.demo.etd.GroupMemberETD;
import com.example.demo.repository.GroupMemberETDRepository;
import com.example.demo.service.GroupMemberETDService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GroupMemberETDServiceImpl implements GroupMemberETDService {
    @Autowired
    private GroupMemberETDRepository groupMemberETDRepository;
    @Override
    public List<GroupMemberETD> findAll() {
        return (List<GroupMemberETD>) groupMemberETDRepository.findAll();
    }

    @Override
    public Optional<GroupMemberETD> findById(Integer id) {
        return groupMemberETDRepository.findById(id);
    }

    @Override
    public GroupMemberETD save(GroupMemberETD groupMemberETD) {
        return groupMemberETDRepository.save(groupMemberETD);
    }

    @Override
    public void deleteById(Integer id) {
        groupMemberETDRepository.deleteById(id);
    }
}
