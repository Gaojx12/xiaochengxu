package cn.homyit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户信息
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    //private int age;
    private String password;
    private String sex;
    private LocalDate birth;
    private String city;
    private String tel;
    private Integer point;
    private LocalDateTime createTime;
    private String photoUrl;

}
