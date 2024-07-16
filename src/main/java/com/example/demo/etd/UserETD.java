package com.example.demo.etd;

import com.example.demo.entity.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "User",timeToLive = 3600)
public class UserETD {
    @Id
    private int id;
    private User data;
}
