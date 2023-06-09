package com.qianfeng.service;

import com.qianfeng.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll();

    void save(Permission permission);

    List<Permission> findRoleByIdAndAllPermission(String id);
}
