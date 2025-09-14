package cn.homyit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_sku")
public class ProductSku {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long productId;
    private String skuCode;   // 唯一SKU标识
    private String specs;     // JSON，例：{"颜色":"黑色","内存":"8GB"}
    private BigDecimal price; // 单独定价
    private Integer stock;    // 库存
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

