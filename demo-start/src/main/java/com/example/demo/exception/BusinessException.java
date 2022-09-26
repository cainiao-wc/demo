package com.example.demo.exception;

/**
 *
 * @author wuchang006
 * date 2022-09-26:21
 * note: 业务异常
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
