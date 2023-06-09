package com.qianfeng.service.impl;

import com.github.pagehelper.PageHelper;
import com.qianfeng.dao.IOrderDao;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;
import com.qianfeng.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private IOrderDao orderDao;

    @Override
    public List<Orders> findAll(Integer page, Integer pageSize) {
        //这行代码只能写在findALL上面
        PageHelper.startPage(page,pageSize);//将来框架会自动帮我们在sql上拼接limit关键字
        List<Orders> ordersList =orderDao.findAll();
        return ordersList;
    }

    @Override
    public Orders findById(String id) {
        return orderDao.findById(id);
    }
}
