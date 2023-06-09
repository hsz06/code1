package com.qianfeng.service.impl;

import com.qianfeng.dao.IRoleDao;
import com.qianfeng.domain.Role;
import com.qianfeng.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

//角色的业务层实现类
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll(){

        List<Role>roleList=roleDao.findAll();
        return roleList;
    }

    @Override
    public void save(Role role) {
        role.setId(UUID.randomUUID().toString());
        roleDao.save(role);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return roleDao.findUserByIdAndAllRole(id);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void addRoleToPermission(String roleId, String[] ids) {
        for (String permissionId : ids) {
            roleDao.addRoleToPermission(roleId,permissionId);
        }
    }
}
