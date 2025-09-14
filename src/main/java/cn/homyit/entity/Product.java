package cn.homyit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//产品信息
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String desp;//描述
    private BigDecimal price;//商品最低价格，固定值
    private Integer sales;
    private Integer num;
    private String imageUrl;
    private Boolean isActive; // 是否参与活动
    private LocalDateTime activityEndTime; // 活动结束时间
    private Long categoryId;//货架号


}
