package cn.homyit.mapper;

import cn.homyit.Dto.CartItemDto;
import cn.homyit.entity.CartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {
    // 新增购物车商品
    @Insert("""
        INSERT INTO cart_items(user_id, product_id, sku_id, quantity,create_time)
        VALUES(#{userId}, #{productId}, #{skuId}, #{quantity},#{createTime})
        ON DUPLICATE KEY UPDATE quantity = quantity + #{quantity}
    """)
    int insertCartItem(CartItem cartItem);


    // 更新购物车商品数量和更新时间
//    @Update("UPDATE cart_items SET quantity = #{quantity}, create_time = NOW() WHERE id = #{id}")
//    int updateCartItemQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);


    // 删除购物车商品
    @Delete("DELETE FROM cart_items WHERE user_id = #{userId} AND product_id = #{productId} AND sku_id = #{skuId}")
    int deleteCartItem(@Param("userId") Long userId, @Param("productId") Long productId,@Param("skuId") Long skuId);

    // 查询用户购物车所有商品
    @Select("""
    SELECT
        c.id,
        c.product_id,
        c.sku_id,
        p.name,
        p.desp,
        p.image_url AS imageUrl,
        s.price,
        s.specs,
        c.quantity
    FROM cart_items c
    LEFT JOIN product p ON c.product_id = p.id
    LEFT JOIN product_sku s ON c.sku_id = s.id
    WHERE c.user_id = #{userId}
""")
    List<CartItemDto> findCartItemsByUserId(Long userId);

    // 查询购物车中是否有该商品（用户+商品唯一）
    @Select("SELECT * FROM cart_items WHERE user_id = #{userId} AND product_id = #{productId} AND sku_id = #{skuId}")
    CartItem findCartItem(@Param("userId") Long userId, @Param("productId") Long productId, @Param("skuId") Long skuId);

    // 更新已有购物车数量
    @Update("""
        UPDATE cart_items
        SET quantity = #{quantity}
        WHERE id = #{id}
    """)
    int updateCartItemQuantity(CartItem item);
}
