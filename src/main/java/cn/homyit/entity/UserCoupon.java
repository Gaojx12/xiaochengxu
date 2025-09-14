package cn.homyit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//用户优惠券
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCoupon {

    private Long id;              // 记录ID
    private Long userId;          // 用户ID
    private Long couponId;        // 优惠券ID
    private String status;        // 优惠券状态：UNUSED, USED, EXPIRED
    private LocalDateTime usedTime;   // 使用时间
    private LocalDateTime createTime; // 领取时间
}