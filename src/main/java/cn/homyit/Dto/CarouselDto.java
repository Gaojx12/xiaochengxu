package cn.homyit.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data

public class CarouselDto {

    public Long id;

    @NotBlank
    @Size(min = 1, max = 255, message = "标题长度必须在1到255之间")
    public String title;

    //@NotBlank
    @Size(min = 5, max = 255, message = "请检查图片")
    public String imageUrl;

    @NotNull
    @Size(min = 0, max = 255, message = "图片描述不得超过255")
    public String description;
}
