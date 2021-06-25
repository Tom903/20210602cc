package com.gao.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.SpuInfo;

public interface SpuInfoService extends IService<SpuInfo> {
    IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> page1, SpuInfo spuInfo);

    void saveSpuInfo(SpuInfo spuInfo);
}
