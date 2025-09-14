package cn.homyit.mapper;

import cn.homyit.entity.ProductSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    /**
     * 查询某商品所有SKU
     */
    @Select("SELECT * FROM product_sku WHERE product_id = #{productId}")
    List<ProductSku> findByProductId(@Param("productId") Long productId);

    @Select("SELECT * FROM product_sku WHERE product_id = #{productId} AND specs = #{specs} LIMIT 1")
    ProductSku findByProductIdAndSpecs(@Param("productId") Long productId, @Param("specs") String specs);


}
