package com.example.demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wuchang006
 * date 2022-09-16:12
 * note: 测试接口集合
 */

@Api(tags = {"测试接口"})
@Slf4j
@RestController
public class WelcomeController {

    @Autowired
    private DataProcessCron dataProcessCron;

    @ApiOperation(value = "欢迎语")
    @GetMapping(value = "/hello")
    public String hello() {
//        dataProcessCron.executeTask();
        System.out.println(dataProcessCron.judgeDate());
        return "hello world";
    }
}
