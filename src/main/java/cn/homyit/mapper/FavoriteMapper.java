package cn.homyit.mapper;

import cn.homyit.Dto.FavoriteDto;
import cn.homyit.entity.Favorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;


import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FavoriteMapper  {

    @Insert("INSERT INTO favorites (user_id, product_id,sku_id,create_time) VALUES (#{userId}, #{productId}, #{skuId},#{createTime})")
    void addFavorite(Favorite favorite);

    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND product_id = #{productId} AND sku_id = #{skuId}")
    void removeFavorite(@Param("userId") Long userId, @Param("productId") Long productId, @Param("skuId") Long skuId);


    @Select("""
    SELECT
        f.id,
        f.product_id,
        f.sku_id,
        p.name,
        p.desp,
        p.image_url AS imageUrl,
        s.price,
        s.specs
    FROM favorites f
    LEFT JOIN product p ON f.product_id = p.id
    LEFT JOIN product_sku s ON f.sku_id = s.id
    WHERE f.user_id = #{userId}
""")
    List<FavoriteDto> getFavoriteProducts(@Param("userId") Long userId);

    //避免重复收藏
    @Select("SELECT COUNT(*) FROM favorites WHERE user_id = #{userId} AND product_id = #{productId} AND sku_id = #{skuId}")
    int exists(@Param("userId") Long userId, @Param("productId") Long productId,@Param("skuId") Long skuId);

    @Select("SELECT * FROM favorites WHERE user_id = #{userId}  AND product_id = #{productId} AND sku_id = #{skuId}")
    Favorite findByUserIdAndSkuId(@Param("userId") Long userId, @Param("productId") Long productId,@Param("skuId") Long skuId);

}

