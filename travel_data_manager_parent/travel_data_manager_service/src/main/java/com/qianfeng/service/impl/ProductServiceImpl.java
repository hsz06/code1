package com.qianfeng.service.impl;

import com.qianfeng.dao.IProductDao;
import com.qianfeng.domain.Product;
import com.qianfeng.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service//框架帮我们创建这个项目
public class ProductServiceImpl implements IProductService {
    @Resource
    private IProductDao productDao;
    @Override
    public List<Product> findAll() {
        return productDao.findALL();
    }

    @Override
    public void add(Product product) {
        String id = UUID.randomUUID().toString();//生成一个全球唯一的字符串当作数据库的ID
        product.setId(id);
        productDao.add(product);

    }

    @Override
    public void deleteByIds(String[] ids) {
        //delet from product where id = #{id}
        //循环一次删除一次
        for (String id : ids) {
            productDao.deleteById(id);
        }
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void update(Product product) {
       productDao.update(product);
    }
}
