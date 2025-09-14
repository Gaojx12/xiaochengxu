package cn.homyit.entity.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program:
 * @description:
 * @author: Answer
 * @create: 2025/02/26 21:52
 **/

@Getter
@AllArgsConstructor
public enum TagEnum {
    ZERO(0, "名词解释"),
    FIRST(1, "政策方案"),
    SECOND(2, "会议讲话"),
    THIRD(3, "文献检索"),
    FOURTH(4, "其他"),
    FIFTH(5, "用户上传");
    private final Integer code;
    private final String message;

}
