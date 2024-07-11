package com.example.demo.etd;

import com.example.demo.entity.dao.GroupMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "GroupMember",timeToLive = 3600)
public class GroupMemberETD  {
    @Id
    private int id;
    private GroupMember data;
}
