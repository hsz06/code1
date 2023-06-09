package com.qianfeng.controller;

import com.qianfeng.domain.Product;
import com.qianfeng.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller//需要框架来创建这个类的对象
@RequestMapping("/product")
public class ProductController {
    @Resource
    private IProductService productService;//表示找到框架帮助我们创建对象
    @RequestMapping("/findALL")
    public ModelAndView findALL(){//返回值类型 ModelAndView 模型（数据）以及视图（jsp）
        ModelAndView mav=new ModelAndView();
        //调用业务层函数获取数据
        List<Product> productList = productService.findAll();
        mav.addObject("productList",productList);//填充模型
        mav.setViewName("product-list");
        return mav;
    }
    @RequestMapping("/add")
    public String  add(Product product){//添加完成之后，应该再次查询所有
        productService.add(product);
        return "redirect:/product/findALL";//重新定向到查询
          }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mav=new ModelAndView();
        //调用业务层函数获取数据
        Product product = productService.findById(id);
        mav.addObject("product",product);//填充模型
        mav.setViewName("product-update");
        return mav;
    }
    @RequestMapping("/update")
    public String  update(Product product){//修改完成之后，应该再次查询所有
        productService.update(product);
        return "redirect:/product/findALL";//重新定向到查询
    }
    @RequestMapping("/deleteByIds")
    public String deleteByIds(String[] ids){//前端会传递多个id
        productService.deleteByIds(ids);
        return "redirect:/product/findALL";//重新定向到查询

    }
}
