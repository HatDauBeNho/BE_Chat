package com.example.demo.controller;

import com.example.demo.custom.auth.response.CustomResponse;
import com.example.demo.custom.auth.response.SignInResponse;
import com.example.demo.custom.group.request.CreateGroupRequest;
import com.example.demo.custom.group.response.CreateGroupResponse;
import com.example.demo.entity.dao.Group;
import com.example.demo.entity.dao.GroupMember;
import com.example.demo.entity.dao.User;
import com.example.demo.security.service.UserDetailsImpl;
import com.example.demo.service.GroupChatService;
import com.example.demo.service.GroupMemberService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/message/group")
public class GroupChatController {
    @Autowired
    private GroupChatService groupChatService;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    @Transactional
    public ResponseEntity<?> createGroupChat(@RequestBody CreateGroupRequest createGroupRequest)
    {
        try
        {
            Optional<Group> groupOptional= groupChatService.findByGroupName((createGroupRequest.getGroupName()));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            if (groupOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "Group chat already exists"));
            }

            LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Group group=new Group();
            group.setGroupName(createGroupRequest.getGroupName());
            group.setAdmin(userService.findUserById(userDetails.getUserID()).get());
            group.setCreatedAt(time);

            groupChatService.createGroup(group);

            GroupMember groupMember1=new GroupMember();
            groupMember1.setGroup(group);
            groupMember1.setUser(userService.findUserById(userDetails.getUserID()).get());
            groupMember1.setCreatedAt(time);
            groupMemberService.createGroupMember(groupMember1);
            for (Integer item:createGroupRequest.getMembers())
            {
                Optional<User> friendOptional= userService.findUserById(item);
                if (friendOptional.isEmpty()) {
                    return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, "User is not exists"));
                }
                GroupMember groupMember=new GroupMember();
                groupMember.setGroup(group);
                groupMember.setUser(friendOptional.get());
                groupMember.setCreatedAt(time);
                groupMemberService.createGroupMember(groupMember);
            }
//            System.out.println(groupMemberService.findUserIdInOneGroup(group.getGroupID()));
            return  ResponseEntity.ok()
                    .body(new CustomResponse<>(
                            1,null,
//                            new CreateGroupResponse(
//                                    group.getGroupID(),group.getGroupName(),group.getAdmin().getUserId(),
//                                    groupMemberService.findUserIdInOneGroup(group.getGroupID()),time
//                            ),
                            "Success create group")
                    );

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, e.getMessage(), null));
        }
    }
}
