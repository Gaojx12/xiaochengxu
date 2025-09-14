package cn.homyit.mapper;

import cn.homyit.Dto.ProductSpecDto;
import cn.homyit.entity.ProductSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductSpecMapper extends BaseMapper<ProductSpec> {


    /**
     * 查询某商品的所有规格
     */
    @Select("SELECT * FROM product_spec WHERE product_id = #{productId}")
    List<ProductSpec> findByProductId(@Param("productId") Long productId);

    @Select("SELECT " +
            "ps.id AS id, " +
            "ps.product_id AS productId, " +
            "ps.name AS specName, " +
            "GROUP_CONCAT(psv.value ORDER BY psv.id) AS specValues " +
            "FROM product_spec ps " +
            "LEFT JOIN product_spec_value psv ON ps.id = psv.spec_id " +
            "WHERE ps.product_id = #{productId} " +
            "GROUP BY ps.id, ps.product_id, ps.name")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "productId", property = "productId"),
            @Result(column = "specName", property = "specName"),
            // 将逗号分隔的字符串转成 List<String>
            @Result(column = "specValues", property = "specValues",
                    typeHandler = cn.homyit.handler.StringToListTypeHandler.class)
    })
    List<ProductSpecDto> selectSpecsWithValues(@Param("productId") Long productId);

}
