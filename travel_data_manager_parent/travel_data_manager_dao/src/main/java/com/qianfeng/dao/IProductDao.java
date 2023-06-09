package com.qianfeng.dao;

import com.qianfeng.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//让框架帮助我们创建对象
public interface IProductDao {
    @Select("select * from product")
    List<Product> findALL();
    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void add(Product product);
    @Select("select * from product where id = #{id}")
    Product findById(String id);

    //只是修改产品名称
    @Update("update product set productName = #{productName} where id = #{id}")
    void update(Product product);
    @Delete("delete from product where id = #{id}")
    void deleteById(String id);
}
