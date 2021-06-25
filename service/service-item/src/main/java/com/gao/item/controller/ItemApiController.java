package com.gao.item.controller;

import com.gao.common.result.Result;
import com.gao.item.service.ItemService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/item")
public class ItemApiController {

    @Autowired
    private ItemService itemService;

    @GetMapping("{skuId}")
    public Result getItemBySkuId(@PathVariable Long skuId){
        //  调用服务层方法
        Map<String, Object> map = itemService.getBySkuId(skuId);
        //  返回给web-all 使用
        return Result.ok(map);
    }


}
