package cn.homyit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Long id;               // 购物车项ID
    private Long userId;           // 用户ID
    private Long productId;        // 商品ID
    private Long skuId;             // SKU ID
    private Integer quantity;      // 商品数量
    private LocalDateTime createTime;  // 加入购物车的时间

}

