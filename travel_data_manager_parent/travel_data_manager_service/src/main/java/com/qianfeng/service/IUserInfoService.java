package com.qianfeng.service;

import com.qianfeng.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService extends UserDetailsService {
    List<UserInfo> findALL();//遵循springsecurity的要求实现关系

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    void addRoleToUser(String userId, String[] ids);
}
