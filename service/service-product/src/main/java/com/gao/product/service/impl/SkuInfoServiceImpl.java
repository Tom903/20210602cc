package com.gao.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.common.execption.GmallException;
import com.gao.model.product.SkuAttrValue;
import com.gao.model.product.SkuImage;
import com.gao.model.product.SkuInfo;
import com.gao.model.product.SkuSaleAttrValue;
import com.gao.product.mapper.SkuAttrValueMapper;
import com.gao.product.mapper.SkuImageMqpper;
import com.gao.product.mapper.SkuInfoMapper;
import com.gao.product.mapper.SkuSaleAttrValueMapper;
import com.gao.product.service.SkuInfoService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMqpper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    public void saveSkuInfo(SkuInfo skuInfo) {
        skuInfoMapper.insert(skuInfo);
        List<SkuImage> list1 = skuInfo.getSkuImageList();
        if (!CollectionUtils.isEmpty(list1)) {
            for (SkuImage skuImage : list1) {
                skuImage.setSkuId(skuInfo.getId());
                skuImageMapper.insert(skuImage);
            }
        }
        List<SkuAttrValue> list2 = skuInfo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(list2)) {
            for (SkuAttrValue skuAttrValue : list2) {
                skuAttrValue.setSkuId(skuInfo.getId());
                skuAttrValueMapper.insert(skuAttrValue);
            }
        }
        List<SkuSaleAttrValue> list3 = skuInfo.getSkuSaleAttrValueList();
        if (CollectionUtils.isEmpty(list3)) {
            for (SkuSaleAttrValue skuSaleAttrValue : list3) {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
                skuSaleAttrValueMapper.insert(skuSaleAttrValue);
            }
        }
    }

    @Override
    public IPage<SkuInfo> pageList(Page<SkuInfo> skuInfoPage) {
        Page<SkuInfo> page = ChainWrappers.lambdaQueryChain(skuInfoMapper).orderByDesc().page(skuInfoPage);
        return page;
    }

    @Override
    public void onSale(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        if (StringUtils.isEmpty(skuInfo)) {
            throw new GmallException("sku无数据", 100);
        }
        skuInfo.setIsSale(1);
        skuInfoMapper.updateById(skuInfo);
    }

    @Override
    public void cancelSale(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        if (StringUtils.isEmpty(skuInfo)) {
            throw new GmallException("sku无数据", 100);
        }
        skuInfo.setIsSale(0);
        skuInfoMapper.updateById(skuInfo);
    }

    @Override
    public SkuInfo getSkuInfo(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        return skuInfo;
    }

    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        if (skuInfo != null) {
            return skuInfo.getPrice();
        } else {
            return new BigDecimal(0);
        }
    }


}
