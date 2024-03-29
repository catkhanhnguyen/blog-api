package com.blog.demo.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blog.demo.data.response.UserInfoResponse;
import com.blog.demo.model.UserInfo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class Mapper {
    @Autowired
    private ModelMapper mapper;

    public UserDetails convertToUserDetails(UserInfo user){
        return mapper.map(user, UserDetails.class);
    }

    public UserInfoResponse convertTUserInfoResponse(UserInfo user){
        return mapper.map(user, UserInfoResponse.class);
    }

    
}
