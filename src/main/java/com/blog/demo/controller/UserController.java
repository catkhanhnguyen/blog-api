package com.blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.data.model.TokenModel;
import com.blog.demo.data.request.LoginRequest;
import com.blog.demo.data.request.UserInfoRequest;
import com.blog.demo.data.response.UserInfoResponse;
import com.blog.demo.service.UserInfoService;
import com.blog.demo.utils.Mapper;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private Mapper mapper;

    @PostMapping("/register")
    public ResponseEntity<UserInfoResponse> register(@RequestBody UserInfoRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertTUserInfoResponse(service.create(request)));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenModel> login(@RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.login(request));
    }

    // @PostMapping("/refresh-token")
    // public ResponseEntity<TokenModel> refreshToken(@RequestBody TokenModel request){
    //     return ResponseEntity.status(HttpStatus.OK).body(service.refreshToken(request));
    // }
}
