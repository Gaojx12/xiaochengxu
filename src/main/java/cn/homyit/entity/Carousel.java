package cn.homyit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("carousel")
@ToString
public class Carousel {
    //自增主键，递增
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;
    @TableField("image_url")
    private String imageUrl;
    private String description;

}