package cn.homyit.service.impl;

import cn.homyit.Dto.ProductSpecDto;
import cn.homyit.entity.ProductSpec;
import cn.homyit.entity.ProductSpecValue;
import cn.homyit.entity.Result;
import cn.homyit.mapper.ProductSpecMapper;
import cn.homyit.mapper.ProductSpecValueMapper;
import cn.homyit.service.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Autowired
    private ProductSpecValueMapper productSpecValueMapper;

    //增加规格--->如增加内存，颜色
    @Transactional(rollbackFor = Exception.class)//下面的事务要同时成功
    @Override
    public Result addSpec(ProductSpecDto dto) {
        ProductSpec spec = new ProductSpec();
        spec.setProductId(dto.getProductId());
        spec.setName(dto.getSpecName());
        productSpecMapper.insert(spec);

        for (String value : dto.getSpecValues()) {
            ProductSpecValue specValue = new ProductSpecValue();
            specValue.setSpecId(spec.getId());
            specValue.setValue(value);
            productSpecValueMapper.insert(specValue);
        }
        return Result.success("增加该商品的规格成功");
    }
    //列出该商品的所有规格
    public List<ProductSpecDto> listSpecs(Long productId) {
        return productSpecMapper.selectSpecsWithValues(productId);
    }
}
