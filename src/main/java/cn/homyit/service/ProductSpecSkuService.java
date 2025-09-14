package cn.homyit.service;

import cn.homyit.entity.ProductSku;
import cn.homyit.entity.Result;

import java.util.List;

public interface ProductSpecSkuService {
    Result generateSku(Long productId);
    List<ProductSku> listSku(Long productId);
}
