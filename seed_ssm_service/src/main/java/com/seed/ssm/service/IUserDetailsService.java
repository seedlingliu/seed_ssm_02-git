package com.seed.ssm.service;

import com.seed.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserDetailsService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    UserInfo findById(int id) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    void addRoleToUser(int userId, int[] roleIds) throws Exception;
}
