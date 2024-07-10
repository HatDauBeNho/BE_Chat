package com.example.demo.etd;

import com.example.demo.entity.dao.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Group", timeToLive = 3600)
public class GroupChatETD
{
    @Id
    private Integer id;
    private Group data;
}
