package com.qianfeng.service.impl;

import com.qianfeng.dao.IUserInfoDao;
import com.qianfeng.domain.Role;
import com.qianfeng.domain.UserInfo;
import com.qianfeng.service.IUserInfoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service(value="userService")//仅限于此处需要写Value
public class UserInfoServiceImpl implements IUserInfoService {
    @Resource
    private IUserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        //这里是从数据库自己查询的
        UserInfo userInfo=userInfoDao.findByUsername(username);
        if (userInfo==null){
            return null;
        }
        //user对象是框架要求，四个True，账户是否开启，过期，锁定,授权
        //SpringSecurity框架要求，用户需要具备角色，多个角色，一个角色赋予多个用户
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0? true : false,true,true,true,getGrantedAuthority(userInfo.getRoles()));
        return user;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthority(List<Role> roles) {
        //相当于我们去给从数据库中查询到的用户赋予权限
        ArrayList<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));

        }
        //假数据

        return list;
    }

    @Override
    public List<UserInfo> findALL() {
        return userInfoDao.findAll();
    }
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void save(UserInfo userInfo) {
        userInfo.setId(UUID.randomUUID().toString());
        userInfo.setStatusStr("1");
        //明文变密文
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        //如何维护中间表
        for (String roleId : ids) {
            //遍历一次往中间表中插入一次
            userInfoDao.addRoleToUser(userId,roleId);
        }
    }
}
