package com.example.oceanbioserver.common;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    /**
     * 构造方法
     * @param message 错误信息
     */
    public BusinessException(String message) {
        super(message);
        this.code = Result.FAIL_CODE;
    }

    /**
     * 构造方法
     * @param code 错误码
     * @param message 错误信息
     */
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 获取错误码
     * @return 错误码
     */
    public int getCode() {
        return code;
    }
} 