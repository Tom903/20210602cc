package com.gao.product.controller;

import com.gao.common.result.Result;
import com.gao.model.product.BaseCategory1;
import com.gao.model.product.BaseCategory2;
import com.gao.model.product.BaseCategory3;
import com.gao.product.service.Category2Service;
import com.gao.product.service.Category3Service;
import com.gao.product.service.ManageService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台数据接口")
@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class BaseManageController {

    @Autowired
    private ManageService manageService;

    @Autowired
    private Category2Service category2Service;
    @Autowired
    private Category3Service category3Service;

    /**
     * 查询所有的一级分类数据：
     * http://api.gmall.com/admin/product/getCategory1
     */

    @GetMapping("getCategory1")
    public Result getCategory1() {
        List<BaseCategory1> list = manageService.getAll();
        return Result.ok(list);
    }

    /**
     * 查询所有二级分类数据
     * http://api.gmall.com/admin/product/getCategory2/{category1Id}
     *
     * @param category1Id
     * @return
     */

    @GetMapping("getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable Long category1Id) {
        List<BaseCategory2> list = category2Service.getCategory2(category1Id);
        return Result.ok(list);
    }

    //    查询所有三类数据
//    http://api.gmall.com/admin/product/getCategory3/{category2Id}
    @GetMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable Long category2Id) {
        List<BaseCategory3> list = category3Service.getCategory3(category2Id);
        return Result.ok(list);
    }
}
