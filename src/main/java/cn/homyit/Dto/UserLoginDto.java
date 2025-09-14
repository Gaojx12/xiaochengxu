package cn.homyit.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginDto {
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 50, message = "用户名长度必须在1到50之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 1, max = 255, message = "密码长度必须在1到255之间")
    private String password;
}
