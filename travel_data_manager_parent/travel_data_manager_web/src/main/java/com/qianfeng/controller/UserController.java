package com.qianfeng.controller;

import com.qianfeng.domain.Product;
import com.qianfeng.domain.Role;
import com.qianfeng.domain.UserInfo;
import com.qianfeng.service.IRoleService;
import com.qianfeng.service.IUserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

//用户表现层接口
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserInfoService userInfoService;
    @Resource
    private IRoleService roleService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<UserInfo> userInfoList=userInfoService.findALL();
        ModelAndView mav=new ModelAndView();
        mav.addObject("userList",userInfoList);
        mav.setViewName("user-list");
        return mav;

    }
    @RequestMapping("/save")
    public String save(UserInfo userInfo){

        userInfoService.save(userInfo);
        return "redirect:/user/findAll";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mav=new ModelAndView();
        //调用业务层函数获取数据
        UserInfo userInfo = userInfoService.findById(id);
        mav.addObject("user",userInfo);//填充模型
        mav.setViewName("user-show");
        return mav;
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        List<Role> roleList=roleService.findUserByIdAndAllRole(id);
        UserInfo userInfo=userInfoService.findById(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("user",userInfo);
        mav.addObject("roleList",roleList);
        mav.setViewName("user-role-add");
        return mav;
    }
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,String[] ids){
        //维护中间表
        userInfoService.addRoleToUser(userId,ids);
        return "redirect:/user/findAll";
    }
}
