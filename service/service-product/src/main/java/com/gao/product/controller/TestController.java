package com.gao.product.controller;

import com.gao.common.result.Result;
import com.gao.product.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试锁")
@RestController
@RequestMapping("admin/product/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("testLock")
    public Result testLock() {
        testService.testLock();
        return Result.ok();
    }

    @GetMapping("read")
    public Result<String> read(){
        String msg = testService.readLock();

        return Result.ok(msg);
    }

    @GetMapping("write")
    public Result<String> write(){
        String msg = testService.writeLock();

        return Result.ok(msg);
    }
}
