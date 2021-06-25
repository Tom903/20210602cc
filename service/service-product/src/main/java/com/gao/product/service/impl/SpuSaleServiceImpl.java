package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.SpuSaleAttr;
import com.gao.model.product.SpuSaleAttrValue;
import com.gao.product.mapper.SpuSaleAttrValueMapper;
import com.gao.product.mapper.SpuSaleMapper;
import com.gao.product.service.SpuSaleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SpuSaleServiceImpl extends ServiceImpl<SpuSaleMapper, SpuSaleAttr> implements SpuSaleService {

    @Autowired
    private SpuSaleMapper spuSaleMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Override
    public List<SpuSaleAttr> getlist(Long spuId) {
        List<SpuSaleAttr> list1 = ChainWrappers.lambdaQueryChain(spuSaleMapper).eq(SpuSaleAttr::getSpuId, spuId).list();
        for (SpuSaleAttr spuSaleAttr : list1) {
            List<SpuSaleAttrValue> list = spuSaleAttrValueMapper.getList(spuId);
            if (!CollectionUtils.isEmpty(list)) {
                List<SpuSaleAttrValue> spuSaleAttrValues = new ArrayList<>();
                for (SpuSaleAttrValue spuSaleAttrValue : list) {
                    Long baseSaleAttrId = spuSaleAttrValue.getBaseSaleAttrId();
                    if (baseSaleAttrId == spuSaleAttr.getBaseSaleAttrId()) {
                        spuSaleAttrValues.add(spuSaleAttrValue);
                    }
                }
                spuSaleAttr.setSpuSaleAttrValueList(spuSaleAttrValues);
            }
        }
        return list1;
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        List<SpuSaleAttr> list = spuSaleMapper.getSpuSaleAttrListCheckBySku(skuId, spuId);
        return list;
    }

}
