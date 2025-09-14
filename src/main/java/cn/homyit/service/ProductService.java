package cn.homyit.service;

import cn.homyit.entity.Product;
import cn.homyit.entity.Result;

import java.util.List;

public interface ProductService {
    //1.获取
    List<Product> listAll();

    //2.按id获取轮
    Result getProductById(Long id);

    //3.获取活动商品
    List<Product> getActiveProducts();

    //4.分类排序

    List<Product> getProductsByCategoryAndSort(Long categoryId, String sortBy, String order);

    List<Product> getProductsByCategoryId(Long categoryId);

}
