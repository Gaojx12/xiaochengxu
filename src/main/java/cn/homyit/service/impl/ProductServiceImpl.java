package cn.homyit.service.impl;

import cn.homyit.entity.Product;
import cn.homyit.entity.Result;
import cn.homyit.mapper.ProductMapper;
import cn.homyit.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> listAll() {
        return list();
    }

    @Override
    public Result getProductById(Long id) {
        Product product = this.getById(id);
        if (product == null) {
            return Result.fail("获取失败");
        } else return Result.success(product);
    }

    @Override
    public List<Product> getActiveProducts() {
        List<Product> activeProducts = productMapper.findActiveProducts();

        if (activeProducts != null && !activeProducts.isEmpty()) {
            return activeProducts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Product> getProductsByCategoryAndSort(Long categoryId, String sortBy, String order) {
        return productMapper.findProductsByCategoryAndSort(categoryId, sortBy, order);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productMapper.findByCategoryId(categoryId);
    }

}
