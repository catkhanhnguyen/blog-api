package com.blog.demo.data.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {
    private Long id;
    private String username;
    private String password;
    private List<String> roles;
}
