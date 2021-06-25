package com.gao.all.controller;

import com.gao.common.result.Result;
import com.gao.item.ItemFeignClient;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
    @Autowired
    private ItemFeignClient itemFeignClient;

    //  访问商品详情页面控制器
    //  http://item.gmall.com/39.html;
    @RequestMapping("{skuId}.html")
    public String item(@PathVariable Long skuId, Model model) {
        Result<Map> result = itemFeignClient.getItemBySkuId(skuId);
        model.addAllAttributes(result.getData());
        return "item/index";
    }

}
