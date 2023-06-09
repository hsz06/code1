package com.qianfeng.controller;

import com.github.pagehelper.PageInfo;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;
import com.qianfeng.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/orders")
@Controller
public class OrderController {
    @Resource
    private IOrderService orderService;
    //分页查询，亲段需要传递页码，条数给服务器
    @RequestMapping("/findAll")
    public ModelAndView findALL(@RequestParam(defaultValue = "1",name = "page") Integer page,@RequestParam(defaultValue = "5",name="pageSize")Integer pageSize){
        ModelAndView mav=new ModelAndView();
        //将参数传递给service 进行查询
        List<Orders> ordersList = orderService.findAll(page,pageSize);
        PageInfo<Orders> pageInfo=new PageInfo<>(ordersList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-list");
        return mav;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mav=new ModelAndView();
        //调用业务层函数获取数据
        Orders orders = orderService.findById(id);
        mav.addObject("orders",orders);//填充模型
        mav.setViewName("orders-show");
        return mav;
    }
}
