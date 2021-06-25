package com.gao.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseTrademark;
import com.gao.product.mapper.TrademarkMapper;
import com.gao.product.service.TrademarkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrademarkServiceImpl extends ServiceImpl<TrademarkMapper, BaseTrademark> implements TrademarkService {

    @Autowired
    private TrademarkMapper trademarkMapper;

    @Override
    public List<BaseTrademark> getTrademarkList() {
        return ChainWrappers.lambdaQueryChain(trademarkMapper).list();
    }

    @Override
    public IPage<BaseTrademark> pageList(Page<BaseTrademark> p) {
        Page<BaseTrademark> page = ChainWrappers.lambdaQueryChain(trademarkMapper).page(p);
        return page;
    }

    @Override
    public void updateBaseTrademark(BaseTrademark baseTrademark) {
        ChainWrappers.lambdaUpdateChain(trademarkMapper).eq(BaseTrademark::getId,baseTrademark.getId()).update(baseTrademark);
    }

    @Override
    public void deleteById(Long id) {
        trademarkMapper.deleteById(id);
    }

    @Override
    public void delete(Long id) {
        ChainWrappers.lambdaUpdateChain(trademarkMapper).eq(BaseTrademark::getId, id).remove();
    }

}
