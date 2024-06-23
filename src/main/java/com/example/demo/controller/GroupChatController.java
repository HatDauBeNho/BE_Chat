package com.example.demo.controller;

import com.example.demo.custom.auth.response.CustomResponse;
import com.example.demo.custom.group.request.MemberAndGroupActionRequest;
import com.example.demo.custom.group.request.CreateGroupRequest;
import com.example.demo.custom.group.response.CreateGroupResponse;
import com.example.demo.entity.dao.Group;
import com.example.demo.entity.dao.GroupMember;
import com.example.demo.entity.dao.User;
import com.example.demo.repository.GroupMemberRepository;
import com.example.demo.security.service.UserDetailsImpl;
import com.example.demo.service.GroupChatService;
import com.example.demo.service.GroupMemberService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/message")
public class GroupChatController {
    @Autowired
    private GroupChatService groupChatService;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/createGroup")
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

            GroupMember admin=new GroupMember();
            admin.setGroup(group);
            admin.setUser(userService.findUserById(userDetails.getUserID()).get());
            admin.setCreatedAt(time);
            groupMemberService.createGroupMember(admin);
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
            return  ResponseEntity.ok()
                    .body(new CustomResponse<>(
                            1,
                            new CreateGroupResponse(
                                    group.getGroupID(),group.getGroupName(),group.getAdmin().getUserId(),
                                    groupMemberService.findUserIdInOneGroup(group.getGroupID()),time
                            ),
                            "Success create group")
                    );

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, e.getMessage(), null));
        }
    }


    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getListFriendNotInGroup(@PathVariable("groupId") Integer groupId)
    {
        try
        {
           Optional<Group> optionalGroup=groupChatService.findByGroupId(groupId);
           if (optionalGroup.isEmpty())
           {
               return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"Group is not exits"));
           }
            return  ResponseEntity.ok()
                    .body(new CustomResponse<>(
                            1,
                            groupChatService.getListFriendNotInGroup(groupId),
                            "Success get list friend not in group")
                    );
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }
    }

    @PostMapping("/group")
    @Transactional
    public ResponseEntity<?> addMemberToGroup(@RequestBody MemberAndGroupActionRequest request)
    {
        try
        {
            Optional<Group> optionalGroup=groupChatService.findByGroupId(request.getGroupId());
            if (optionalGroup.isEmpty())
            {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"Group is not exits"));
            }

            LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (Integer item: request.getMembers())
            {
                if (groupMemberService.findUserIdInOneGroup(request.getGroupId()).contains(item))
                {
                    return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"User already in group"));
                }
                Optional<User>  userOptional=userService.findUserById(item);
                if (userOptional.isEmpty())
                {
                    return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"User is not exits"));
                }
                GroupMember groupMember=new GroupMember();
                groupMember.setUser(userOptional.get());
                groupMember.setGroup(optionalGroup.get());
                groupMember.setCreatedAt(time);
                groupMemberService.createGroupMember(groupMember);
            }
            return  ResponseEntity.ok().body(new CustomResponse<>(1,null,"Success add member"));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }
    }

    @DeleteMapping("/group")
    public ResponseEntity<?> deleteMemberFromGroup(@RequestBody MemberAndGroupActionRequest request)
    {
        try
        {
            Optional<Group> optionalGroup=groupChatService.findByGroupId(request.getGroupId());
            if (optionalGroup.isEmpty())
            {
                return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"Group is not exits"));
            }
            for (Integer item: request.getMembers())
            {

                Optional<User>  userOptional=userService.findUserById(item);
                if (userOptional.isEmpty())
                {
                    return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"User is not exits"));
                }
                if (groupMemberService.findUserIdInOneGroup(request.getGroupId()).contains(item))
                {
                    groupMemberService.deleteGroupMember(request.getGroupId(),userOptional.get().getUserId());

                }else  return ResponseEntity.badRequest().body(new CustomResponse<>(0, null,"User isn't in group"));

            }
            return  ResponseEntity.ok().body(new CustomResponse<>(1,null,"Success delete member"));

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }
    }
}
