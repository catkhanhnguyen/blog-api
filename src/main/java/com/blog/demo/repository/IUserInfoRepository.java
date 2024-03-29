package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.demo.model.UserInfo;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
    boolean existsByUsername(String username);
}
