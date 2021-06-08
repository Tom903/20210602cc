package com.gao.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseTrademark;
import java.util.List;

public interface TrademarkService extends IService<BaseTrademark> {

    List<BaseTrademark> getTrademarkList();

    IPage<BaseTrademark> pageList(Page<BaseTrademark> p);

    void updateBaseTrademark(BaseTrademark baseTrademark);

    void deleteById(Long id);

}
