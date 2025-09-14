package cn.homyit.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FavoriteDto {
    private Long id;
    private Long productId;
    private String skuId;
    private String name;
    private String desp;
    private String imageUrl;
    private BigDecimal price;
    private String specs;
}
