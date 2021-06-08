package com.atguigu.gmall.product.client.impl;

import com.atguigu.gmall.product.client.ProductFeignClient;
import com.gao.model.product.BaseCategoryView;
import com.gao.model.product.SkuInfo;
import com.gao.model.product.SpuSaleAttr;
import java.math.BigDecimal;
import java.util.List;

public class ProductDegradeFeignClient implements ProductFeignClient {

    @Override
    public SkuInfo getAttrValueList(Long skuId) {
        return null;
    }

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
}
