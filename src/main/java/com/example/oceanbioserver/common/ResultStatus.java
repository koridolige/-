package com.example.oceanbioserver.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
/*
* 根据您提供的代码，这是一个名为`ResultStatus`的枚举类，定义了两个枚举常量：`SUCCESS`和`SERVER_ERROR`，
* 分别表示成功和服务器内部错误状态。每个枚举常量包含HTTP状态、代码和消息。
通过使用枚举类来定义不同的结果状态，可以更好地管理和维护代码中可能出现的各种状态。这种设计让代码更具可读性和可维护性。
*
* */
@ToString
@Getter
public enum ResultStatus {

    // 成功状态
    SUCCESS(HttpStatus.OK,200,"成功"),

    // 客户端错误
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "错误请求"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "未授权"),
    FORBIDDEN(HttpStatus.FORBIDDEN, 403, "禁止访问"),
    NOT_FOUND(HttpStatus.NOT_FOUND, 404, "未找到资源"),

    // 服务器错误
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "服务器内部错误"),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, 501, "未实现"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, 502, "错误网关"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, 503, "服务不可用");

    private final HttpStatus httpStatus;//返回的状态    成功 或者  服务器内部错误
    private final Integer code;//状态码
    private final String message;//返回的信息

    /**
     *通过构造方法，给枚举类里面变量进行赋值；（属性赋值，值传递过来 赋值给对应的属性）
     */
    ResultStatus(HttpStatus status, Integer code , String message){
        this.httpStatus=status;
        this.code=code;
        this.message=message;
    }

}
