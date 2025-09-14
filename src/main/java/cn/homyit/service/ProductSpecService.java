package cn.homyit.service;

import cn.homyit.Dto.ProductSpecDto;
import cn.homyit.entity.ProductSpec;
import cn.homyit.entity.Result;

import java.util.List;

public interface ProductSpecService {
     Result addSpec(ProductSpecDto dto);
     List<ProductSpecDto> listSpecs(Long productId);

}
