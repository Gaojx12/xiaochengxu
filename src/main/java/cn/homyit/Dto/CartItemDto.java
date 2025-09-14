package cn.homyit.Dto;

import lombok.Data;

import java.math.BigDecimal;

//用来返回用户的
@Data
public class CartItemDto {
    private Long id;    // 购物车ID
    private Long productId;
    private String skuId;
    private String name;
    private String desp;
    private String imageUrl;
    private BigDecimal price;
    private Integer quantity;
    private String specs;     // JSON 规格：{"颜色":"黑色","内存":"8GB"}
}
