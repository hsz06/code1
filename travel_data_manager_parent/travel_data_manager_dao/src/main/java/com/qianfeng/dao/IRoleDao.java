package com.qianfeng.dao;

import com.qianfeng.domain.Member;
import com.qianfeng.domain.Product;
import com.qianfeng.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    @Select("select * from role")
    public List<Role> findAll();
    @Insert("insert into role(id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    void save(Role role);
    @Select("select * from role where id in (select roleId from users_role where userid=#{userId})")
    @Results(id = "baseResult3",value={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(property = "permissions",column = "id",many= @Many(select = "com.qianfeng.dao.IPermissionDao.findByRoleId",fetchType = FetchType.LAZY)),
           //从订单中获取产品id，在product的方法findById进行id查询产品信息
    })
    List<Role> findByUsersId(String id);
    //未关联角色
    @Select("select * from role where id not in (select roleId from users_role where userid=#{userId})")
    List<Role> findUserByIdAndAllRole(String usersId);
    @Select("select * from role where id = #{id}")
    Role findById(String id);
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addRoleToPermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
