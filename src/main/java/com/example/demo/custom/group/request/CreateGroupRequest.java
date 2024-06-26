package com.example.demo.custom.group.request;

import lombok.Data;

import java.util.List;
@Data

public class CreateGroupRequest {
    private String groupName;
    private List<Integer> members;


}
