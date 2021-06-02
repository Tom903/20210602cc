package com.gao.product.controller;

import com.gao.common.result.Result;
import com.gao.model.product.BaseCategory1;
import com.gao.product.service.ManageService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台数据接口")
@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class BaseManageController {

    @Autowired
    private ManageService manageService;

    //  查询所有的一级分类数据：
    //  http://api.gmall.com/admin/product/getCategory1
    @GetMapping("getCategory1")
    public Result getCategory1() {
        List<BaseCategory1> list = manageService.getAll();
        return Result.ok(list);
    }
}
