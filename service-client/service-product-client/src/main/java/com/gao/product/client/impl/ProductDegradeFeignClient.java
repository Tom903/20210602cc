package com.gao.product.client.impl;

import com.gao.model.product.BaseCategoryView;
import com.gao.model.product.SkuInfo;
import com.gao.model.product.SpuSaleAttr;
import com.gao.product.client.ProductFeignClient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProductDegradeFeignClient implements ProductFeignClient {
    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        return null;
    }

    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        return null;
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        return null;
    }

    @Override
    public Map getSkuValueIdsMap(Long spuId) {
        return null;
    }

    @Override
    public SkuInfo getSkuInfo(Long skuId) {
        return null;
    }
}
