package com.example.oceanbioserver.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * @param e 业务异常
     * @return 通用返回
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        log.error("业务异常", e);
        // 修改为使用 failure 方法（注意方法名变化）
        return Result.failure(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     * @param e 参数校验异常
     * @return 通用返回
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Result<?> handleValidException(Exception e) {
        log.error("参数校验异常", e);
        BindingResult bindingResult = null;
        if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }

        StringBuilder sb = new StringBuilder("参数校验失败：");
        if (bindingResult != null && bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                sb.append(fieldError.getDefaultMessage());
            }
        }
        // 修改为使用 failure 方法（注意方法名变化）
        return Result.failure(sb.toString());
    }

    /**
     * 处理其他异常
     * @param e 其他异常
     * @return 通用返回
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("系统异常", e);
        // 修改为使用 failure 方法（注意方法名变化）
        return Result.failure("系统异常，请联系管理员");
    }
}