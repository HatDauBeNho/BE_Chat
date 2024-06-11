package com.example.demo.security.service;

import com.example.demo.entity.dao.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl  implements UserDetails {
    private int userID;
    private String userName;
    private String password;
    private String fullName;
    private String avatar;
    private final Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(int userID, String userName, String password, String fullName,String avatar,Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.avatar = avatar;
        this.fullName = fullName;
        this.password = password;
        this.userName = userName;
        this.userID = userID;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserDetailsImpl(
                user.getUserID(),
                user.getUserName(),
                user.getPassword(),
                user.getFullName(),
                user.getAvatar(),
                authorities
                );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
