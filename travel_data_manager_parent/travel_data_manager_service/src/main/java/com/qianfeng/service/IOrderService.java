package com.qianfeng.service;

import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;

import java.util.List;
//产品的业务接口
public interface IOrderService {
//查询所有信息，返回值结果表示多条产品信息
    List<Orders> findAll(Integer page,Integer pageSize);


    Orders findById(String id);
}
