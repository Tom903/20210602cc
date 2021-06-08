package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.SpuSaleAttr;
import com.gao.product.mapper.SpuSaleMapper;
import com.gao.product.service.SpuSaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpuSaleServiceImpl extends ServiceImpl<SpuSaleMapper, SpuSaleAttr> implements SpuSaleService {
    @Autowired
    private SpuSaleMapper spuSaleNapper;
    @Override
    public List<SpuSaleAttr> getlist(Long spuId) {
        List<SpuSaleAttr> list = ChainWrappers.lambdaQueryChain(spuSaleNapper).eq(SpuSaleAttr::getSpuId, spuId).list();
        return list;
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        List<SpuSaleAttr> list = spuSaleNapper.getSpuSaleAttrListCheckBySku(skuId, spuId);
        return list;
    }

}
