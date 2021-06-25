package com.gao.item.service;

import java.util.Map;

public interface ItemService {
    //  数据接口
    Map<String,Object> getBySkuId(Long skuId);
}
