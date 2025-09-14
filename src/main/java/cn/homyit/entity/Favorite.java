package cn.homyit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("favorites")
public class Favorite {

    private Long id;               // 收藏ID
    private Long userId;           // 用户ID
    private Long productId;        // 商品ID
    private Long skuId;             // SKU ID
    private LocalDateTime createTime;  // 收藏时间

}
