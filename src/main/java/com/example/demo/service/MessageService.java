package com.example.demo.service;

import com.example.demo.custom.message.handle.MessageHandle;

import java.util.Optional;

public interface MessageService {
    Optional<MessageHandle> lastMessage(int id1, int id2);
}
