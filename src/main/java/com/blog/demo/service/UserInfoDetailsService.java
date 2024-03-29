package com.blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.demo.model.UserInfo;
import com.blog.demo.repository.IUserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoDetailsService implements UserDetailsService{
    @Lazy
    @Autowired
    private IUserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password(user.getPassword())
            .authorities(user.getRoles())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }
}
