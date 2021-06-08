package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseSaleAttr;
import java.util.List;

public interface SaleAttrService extends IService<BaseSaleAttr> {

    List<BaseSaleAttr> getList();
}
