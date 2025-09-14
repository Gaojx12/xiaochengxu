package cn.homyit.mapper;

import cn.homyit.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT * FROM product WHERE is_active = 1 AND activity_end_time > NOW()")
    List<Product> findActiveProducts();


    // 按分类和排序查询商品
    @Select("SELECT * FROM product WHERE category_id = #{categoryId} AND is_active = 1")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    // 按分类和排序查询商品
    @Select("<script>" +
            "SELECT * FROM product WHERE category_id = #{categoryId} " +
            "<choose>" +
            "   <when test='sortBy == \"price\"'>" +
            "       ORDER BY price ${order}" +
            "   </when>" +
            "   <when test='sortBy == \"sales\"'>" +
            "       ORDER BY sales ${order}" +
            "   </when>" +
            "   <otherwise>" +
            "       ORDER BY id ASC" +
            "   </otherwise>" +
            "</choose>" +
            "</script>")
    List<Product> findProductsByCategoryAndSort(@Param("categoryId") Long categoryId,
                                                @Param("sortBy") String sortBy,
                                                @Param("order") String order);


}
