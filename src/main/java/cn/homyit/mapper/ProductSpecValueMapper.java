package cn.homyit.mapper;

import cn.homyit.entity.ProductSpecValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductSpecValueMapper extends BaseMapper<ProductSpecValue> {
    /**
     * 根据规格ID查询规格值
     */
    @Select("SELECT value FROM product_spec_value WHERE spec_id = #{specId}")
    List<String> findValuesBySpecId(@Param("specId") Long specId);
}
