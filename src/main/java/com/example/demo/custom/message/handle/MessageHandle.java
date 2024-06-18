package com.example.demo.custom.message.handle;

import java.time.LocalDateTime;

public interface MessageHandle {
    Integer getMessageId();
    String getContent();
    Integer getFromUserId();
    Integer getToUserId();
    Integer getToGroupId();
    LocalDateTime getCreatedAt();
}
