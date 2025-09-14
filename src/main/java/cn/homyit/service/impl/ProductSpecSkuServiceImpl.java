package cn.homyit.service.impl;

import cn.homyit.entity.ProductSku;
import cn.homyit.entity.ProductSpec;
import cn.homyit.entity.Result;
import cn.homyit.mapper.ProductSkuMapper;
import cn.homyit.mapper.ProductSpecMapper;
import cn.homyit.mapper.ProductSpecValueMapper;
import cn.homyit.service.ProductSpecSkuService;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductSpecSkuServiceImpl implements ProductSpecSkuService {
        @Autowired
        private ProductSpecMapper productSpecMapper;

        @Autowired
        private ProductSpecValueMapper productSpecValueMapper;

        @Autowired
        private ProductSkuMapper productSkuMapper;
    //生成SKU
    @Transactional
    @Override
    public Result generateSku(Long productId) {
        // 查询该商品的所有规格
        List<ProductSpec> specs = productSpecMapper.findByProductId(productId);

        // 查询每个规格的所有可选值，并去重
        List<List<String>> specValues = new ArrayList<>();
        for (ProductSpec spec : specs) {
            List<String> values = productSpecValueMapper.findValuesBySpecId(spec.getId());
            specValues.add(values.stream().distinct().toList()); // 去重
        }

        // 生成规格组合（笛卡尔积）
        List<Map<String, String>> skuCombinations = combineSku(specs, specValues);

        // 遍历组合，插入 SKU（带去重）
        for (Map<String, String> skuMap : skuCombinations) {
            // 生成有序 JSON，保证相同组合字符串一致
            String specsJson = buildOrderedSpecsJson(skuMap);

            // 检查数据库是否已有相同 SKU
            ProductSku existing = productSkuMapper.findByProductIdAndSpecs(productId, specsJson);
            if (existing != null) {
                continue; // 跳过已存在的 SKU
            }

            // 插入新的 SKU
            ProductSku sku = new ProductSku();
            sku.setProductId(productId);
            sku.setSpecs(specsJson);
            sku.setSkuCode(UUID.randomUUID().toString());
            sku.setPrice(BigDecimal.ZERO);
            sku.setStock(0);
            productSkuMapper.insert(sku);
        }

        return Result.success("SKU生成成功");
    }

    /**
     * 生成有序 JSON，保证 key 顺序固定
     */
    private String buildOrderedSpecsJson(Map<String, String> skuMap) {
        return new JSONObject(new TreeMap<>(skuMap)).toString(); // TreeMap 会按 key 排序
    }

    /**
     * 组合规格值（笛卡尔积）
     */
    private List<Map<String, String>> combineSku(List<ProductSpec> specs, List<List<String>> specValues) {
        List<Map<String, String>> result = new ArrayList<>();
        backtrack(result, new LinkedHashMap<>(), specs, specValues, 0);
        return result;
    }

    private void backtrack(List<Map<String, String>> result, Map<String, String> temp,
                           List<ProductSpec> specs, List<List<String>> specValues, int index) {
        if (index == specs.size()) {
            result.add(new LinkedHashMap<>(temp));
            return;
        }
        ProductSpec spec = specs.get(index);
        for (String value : specValues.get(index)) {
            temp.put(spec.getName(), value);
            backtrack(result, temp, specs, specValues, index + 1);
            temp.remove(spec.getName());
        }
    }


    @Override
    public List<ProductSku> listSku(Long productId) {
        return productSkuMapper.findByProductId(productId);
    }



}
