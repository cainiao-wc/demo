package com.example.demo.service.entity.common.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

/**
 *
 * @author wuchang006
 * date 2022-09-26:18
 * note: 返回结果状态码枚举
 */
@Getter
@AllArgsConstructor
public enum ResultEnum implements IResult{
    SUCCESS(2001, "接口调用成功"),
    VALIDATE_FAILED(2002, "参数校验失败"),
    COMMON_FAILED(2003, "接口调用失败"),
    FORBIDDEN(2004, "没有权限访问资源");

    private Integer code;
    private String message;
}
