package com.qianfeng.controller;

import com.qianfeng.domain.Permission;
import com.qianfeng.domain.Role;
import com.qianfeng.domain.UserInfo;
import com.qianfeng.service.IPermissionService;
import com.qianfeng.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav=new ModelAndView();
        List<Role> roleList=roleService.findAll();
        mav.addObject("roleList",roleList);
        mav.setViewName("role-list");
        return mav;
    }
    @RequestMapping("/save")
    public String save(Role role){

        roleService.save(role);
        return "redirect:/role/findAll";
    }
    @RequestMapping ("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        List<Permission> permissionList=permissionService.findRoleByIdAndAllPermission(id);
        Role role=roleService.findById(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("role",role);
        mav.addObject("permissionList",permissionList);
        mav.setViewName("role-permission-add");
        return mav;
    }
    @RequestMapping("/addRoleToPermission")
    public String addRoleToPermission(String roleId, String[] ids){
        roleService.addRoleToPermission(roleId,ids);
        return "redirect:/role/findAll";
    }
}
