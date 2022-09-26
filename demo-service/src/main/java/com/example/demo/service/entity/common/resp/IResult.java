package com.example.demo.service.entity.common.resp;

/**
 *
 * @author wuchang006
 * date 2022-09-26:18
 * note: 接口方法定义
 */
public interface IResult {
    /**
     * 获取状态码
     *
     * @param
     * @return
     */
    Integer getCode();

    /**
     * 获取状态信息描述
     *
     * @param
     * @return
     */
    String getMessage();
}
