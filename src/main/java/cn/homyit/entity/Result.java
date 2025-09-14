package cn.homyit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program:
 * @description: 返回结果实体类
 * @author: Answer
 * @create: 2024/6/20 18:26
 **/
@Getter
@Setter
@NoArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    private Boolean status;

    public Result(Integer code, String message, T data, Boolean status) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public Result(Integer code, String message, Boolean status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        result.setStatus(true);
        return result;
    }

    public static <T> Result<T> success(String message, Boolean status) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setStatus(status);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setData(data);
        result.setStatus(true);
        return result;
    }

    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setStatus(false);
        return result;
    }

    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(664);
        result.setMessage(message);
        result.setStatus(false);
        return result;
    }
}
