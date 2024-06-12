package com.example.demo.service.impl;

import com.example.demo.custom.message.handle.MessageHandle;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired

    private MessageRepository messageRepository;

    @Override
    public Optional<MessageHandle> lastMessage(int id1, int id2)
    {
        return  messageRepository.lastMessage(id1,id2);
    }
}
