package cn.homyit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecValue {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long specId;
    private String value;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
