package cn.homyit.Dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserUpdateDto {

    @Length(min = 1, max = 50, message = "用户名长度必须在1到50之间")
    private String username;

    @Length(min = 1, max = 255, message = "密码长度必须在1到255之间")
    private String password;

    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String city;

    @Length(message = "电话是11位")
    private String tel;

    private String photoUrl;
}