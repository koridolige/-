package com.example.oceanbioserver.common;

import com.example.oceanbioserver.common.ResultStatus;
import lombok.Data;
import lombok.ToString;

/**
 * 通用返回结果类
 * @param <T> 返回数据类型
 */
@ToString
@Data
public class Result<T> {

    /**
     * 业务成功码
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 业务失败码
     */
    public static final int FAIL_CODE = 500;

    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 返回的消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    // 构造方法

    /**
     * 使用枚举状态和数据的构造方法
     * @param resultStatus 结果状态枚举
     * @param data 返回数据
     */
    public Result(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    /**
     * 使用自定义状态码、消息和数据的构造方法
     * @param code 状态码
     * @param message 消息
     * @param data 数据
     */
    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功相关方法

    /**
     * 返回成功结果，使用默认成功状态码和消息
     * @param data 返回数据
     * @return 成功结果
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultStatus.SUCCESS, data);
    }

    /**
     * 返回成功结果，不带数据
     * @return 成功结果
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 返回成功结果，使用自定义枚举状态和数据
     * @param resultStatus 结果状态枚举
     * @param data 返回数据
     * @return 成功结果
     */
    public static <T> Result<T> success(ResultStatus resultStatus, T data) {
        return new Result<>(resultStatus, data);
    }

    /**
     * 返回成功结果，自定义消息和数据
     * @param message 自定义消息
     * @param data 返回数据
     * @return 成功结果
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(SUCCESS_CODE, message, data);
    }

    /**
     * 返回成功结果，自定义状态码、消息和数据
     * @param code 自定义状态码
     * @param message 自定义消息
     * @param data 返回数据
     * @return 成功结果
     */
    public static <T> Result<T> success(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    // 失败相关方法

    /**
     * 返回失败结果，使用默认服务器错误状态
     * @return 失败结果
     */
    public static <T> Result<T> failure() {
        return new Result<>(ResultStatus.SERVER_ERROR, null);
    }

    /**
     * 返回失败结果，使用默认服务器错误状态和数据
     * @param data 返回数据
     * @return 失败结果
     */
    public static <T> Result<T> failure(T data) {
        return new Result<>(ResultStatus.SERVER_ERROR, data);
    }

    /**
     * 返回失败结果，自定义消息
     * @param message 自定义消息
     * @return 失败结果
     */
    public static <T> Result<T> failure(String message) {
        return new Result<>(FAIL_CODE, message, null);
    }

    /**
     * 返回失败结果，自定义消息和数据
     * @param message 自定义消息
     * @param data 返回数据
     * @return 失败结果
     */
    public static <T> Result<T> failure(String message, T data) {
        return new Result<>(FAIL_CODE, message, data);
    }

    /**
     * 返回失败结果，使用自定义枚举状态
     * @param resultStatus 结果状态枚举
     * @return 失败结果
     */
    public static <T> Result<T> failure(ResultStatus resultStatus) {
        return new Result<>(resultStatus, null);
    }

    /**
     * 返回失败结果，使用自定义枚举状态和数据
     * @param resultStatus 结果状态枚举
     * @param data 返回数据
     * @return 失败结果
     */
    public static <T> Result<T> failure(ResultStatus resultStatus, T data) {
        return new Result<>(resultStatus, data);
    }

    /**
     * 返回失败结果，自定义状态码和消息
     * @param code 自定义状态码
     * @param message 自定义消息
     * @return 失败结果
     */
    public static <T> Result<T> failure(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 返回失败结果，自定义状态码、消息和数据
     * @param code 自定义状态码
     * @param message 自定义消息
     * @param data 返回数据
     * @return 失败结果
     */
    public static <T> Result<T> failure(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 判断操作是否成功
     * @return 如果操作成功返回true，否则返回false
     */
    public boolean isSuccess() {
        return code != null && code == SUCCESS_CODE;
    }
}