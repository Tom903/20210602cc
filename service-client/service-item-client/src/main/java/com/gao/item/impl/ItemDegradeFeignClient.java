package com.gao.item.impl;

import com.gao.item.ItemFeignClient;
import com.gao.common.result.Result;
import org.springframework.stereotype.Component;

@Component
public class ItemDegradeFeignClient implements ItemFeignClient {

    @Override
    public Result getItemBySkuId(Long skuId) {
        return null;
    }
}
