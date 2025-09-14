package cn.homyit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignIn {
    private Long id;              // 签到记录ID
    private Long userId;          // 用户ID
    private LocalDate signInDate; // 签到日期
    private Integer points;       // 签到积分
    private LocalDateTime createdAt; // 创建时间

}
