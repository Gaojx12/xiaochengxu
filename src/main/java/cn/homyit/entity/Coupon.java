package cn.homyit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//优惠券
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    private Long id;                  // 优惠券ID
    private String type;              // 优惠券类型：DISCOUNT 折扣券, FULL_REDUCTION 满减折扣券, CASH 现金
    private BigDecimal value;         // 优惠券面额
    private BigDecimal minPurchase;   // 满足最低消费金额
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;            // 优惠券状态：ACTIVE, INACTIVE
    private LocalDateTime createTime;     // 创建时间

}