package com.qiaose.entity;

import com.qiaose.entity.enums.ResultMsgEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Result
 * @Description: 返回对象封装
 * @Author qiaosefennv
 * @Date 2022/6/11
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;

    private T data;

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultMsgEnum.SUCCESS.getCode());
        result.setMessage(ResultMsgEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }
    /**
     * 失败
     */
    public static <T> Result<T> error(int code, String message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> success(int code, String message) {
        return new Result<T>(code, message);
    }
}
