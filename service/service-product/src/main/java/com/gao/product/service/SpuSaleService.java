package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.SpuSaleAttr;
import java.util.List;

public interface SpuSaleService extends IService<SpuSaleAttr> {

    List<SpuSaleAttr> getlist(Long spuId);

    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId);

}
