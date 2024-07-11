package com.example.demo.service.impl;

import com.example.demo.etd.GroupChatETD;
import com.example.demo.repository.GroupChatETDRepository;
import com.example.demo.service.GroupChatETDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupChatETDServiceImpl implements GroupChatETDService {
    @Autowired
    private GroupChatETDRepository groupChatETDRepository;

    @Override
    public List<GroupChatETD> findAll() {
        return (List<GroupChatETD>) groupChatETDRepository.findAll();
    }

    @Override
    public Optional<GroupChatETD> findById(Integer id) {
        return groupChatETDRepository.findById(id);
    }

    @Override
    public GroupChatETD save(GroupChatETD groupChatETD) {
        return groupChatETDRepository.save(groupChatETD);
    }

    @Override
    public void deleteById(Integer id) {
        groupChatETDRepository.deleteById(id);
    }
}
