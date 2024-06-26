package com.example.demo.custom.group.request;

import lombok.Data;

import java.util.List;
@Data

public class MemberAndGroupActionRequest {
    private int groupId;
    private List<Integer> members;

    public MemberAndGroupActionRequest(int groupId, List<Integer> members) {
        this.groupId = groupId;
        this.members = members;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }
}
