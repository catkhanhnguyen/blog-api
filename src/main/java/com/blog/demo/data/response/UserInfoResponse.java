package com.blog.demo.data.response;

import java.util.List;

import com.blog.demo.model.Role;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String username;
    List<Role> roles;
}
