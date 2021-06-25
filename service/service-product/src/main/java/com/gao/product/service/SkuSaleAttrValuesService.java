package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.SkuSaleAttrValue;
import java.util.Map;

public interface SkuSaleAttrValuesService extends IService<SkuSaleAttrValue> {
    Map getSkuValueIdsMap(Long spuId);
}
