package com.gao.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.model.product.SpuImage;
import com.gao.model.product.SpuInfo;
import com.gao.model.product.SpuSaleAttr;
import com.gao.model.product.SpuSaleAttrValue;
import com.gao.product.mapper.SpuImageMapper;
import com.gao.product.mapper.SpuInfoMapper;
import com.gao.product.mapper.SpuSaleAttrValueMapper;
import com.gao.product.mapper.SpuSaleMapper;
import com.gao.product.service.SpuImageService;
import com.gao.product.service.SpuInfoService;
import com.gao.product.service.SpuSaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService {
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleMapper spuSaleMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Override
    public IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> page1, SpuInfo spuInfo) {
        IPage<SpuInfo> page = spuInfoMapper.getSpuInfoPage(page1, spuInfo);
        return page;
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        //spuSaleAttrList/spuImageList
        List<SpuImage> list1 = spuInfo.getSpuImageList();
        if (!CollectionUtils.isEmpty(list1)) {
            for (SpuImage spuImage : list1) {
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insert(spuImage);
            }
        }
        List<SpuSaleAttr> list2 = spuInfo.getSpuSaleAttrList();
        if (!CollectionUtils.isEmpty(list2)) {
            for (SpuSaleAttr spuSaleAttr : list2) {
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleMapper.insert(spuSaleAttr);
                List<SpuSaleAttrValue> list3 = spuSaleAttr.getSpuSaleAttrValueList();
                if (!CollectionUtils.isEmpty(list3)) {
                    for (SpuSaleAttrValue spuSaleAttrValue : list3) {
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                    }
                }
            }
        }
        spuInfoMapper.insert(spuInfo);
    }
}
