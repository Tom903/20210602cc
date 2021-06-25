package com.gao.item;

import com.gao.common.result.Result;
import com.gao.item.impl.ItemDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-item", fallback = ItemDegradeFeignClient.class)
public interface ItemFeignClient {

    @GetMapping("api/item/{skuId}")
    Result getItemBySkuId(@PathVariable Long skuId);
}
