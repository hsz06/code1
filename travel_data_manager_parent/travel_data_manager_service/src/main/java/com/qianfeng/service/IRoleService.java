package com.qianfeng.service;

import com.qianfeng.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    List<Role> findUserByIdAndAllRole(String id);

    Role findById(String id);

    void addRoleToPermission(String roleId, String[] ids);
}
