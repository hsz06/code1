package com.qianfeng.dao;

import com.qianfeng.domain.Member;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

//持久层接口
public interface IOrderDao {//mybatis
    //映射
    @Select("select * from orders")
    @Results(id = "baseResult",value={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "orderCount",property = "orderCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.qianfeng.dao.IProductDao.findById",fetchType = FetchType.EAGER)),
            //从订单中获取产品id，在product的方法findById进行id查询产品信息
    })
    public List<Orders> findAll();
    @Select("select * from orders where id = #{id}")
    @Results(id = "baseResult1",value={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "orderCount",property = "orderCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.qianfeng.dao.IProductDao.findById",fetchType = FetchType.EAGER)),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.qianfeng.dao.IMemberDao.findById",fetchType = FetchType.EAGER)),
            @Result(property = "travellers",column = "id",many = @Many(select = "com.qianfeng.dao.ITravellerDao.findByOrdersId",fetchType = FetchType.LAZY)),
            //从订单中获取产品id，在product的方法findById进行id查询产品信息
    })
    Orders findById(String id);
}
