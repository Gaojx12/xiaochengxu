package cn.homyit.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PasswordUpdateDto {
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Length(min = 1, max = 20, message = "密码长度必须在1到20之间")
    private String newPassword;
}
