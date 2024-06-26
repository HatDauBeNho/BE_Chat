package com.example.demo.custom.group.response;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class CreateGroupResponse {
    private int groupId;
    private String groupName;
    private int adminId;
    private List<Integer> members;
    private LocalDateTime createdAt;


}
