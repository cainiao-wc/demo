package com.example.demo.exception;

/**
 *
 * @author wuchang006
 * date 2022-09-26:20
 * note: 网络异常
 */
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message) {
        super(message);
    }
}
