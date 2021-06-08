package com.gao.item.controller;

import com.gao.common.result.Result;
import com.gao.item.service.ItemService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/item")
public class ItemApiController {

    @Autowired
    private ItemService itemService;

    /**
     * 获取sku详情信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("{skuId}")
    public Result getItem(@PathVariable Long skuId) {
        Map<String,Object> map = itemService.getItem(skuId);
        return Result.ok(map);
    }
}
