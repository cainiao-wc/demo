package com.example.demo.controller;


import com.example.demo.service.entity.common.resp.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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


    @ApiOperation(value = "欢迎语")
    @GetMapping(value = "/hello")
    public Result<String> hello() {
        return Result.success("hello world");
    }
}
