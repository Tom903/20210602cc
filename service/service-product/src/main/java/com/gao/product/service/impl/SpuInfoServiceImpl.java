package com.gao.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.SpuImage;
import com.gao.model.product.SpuInfo;
import com.gao.model.product.SpuSaleAttr;
import com.gao.model.product.SpuSaleAttrValue;
import com.gao.product.mapper.SpuInfoMapper;
import com.gao.product.mapper.SpuSaleAttrValueMapper;
import com.gao.product.service.SpuImageService;
import com.gao.product.service.SpuInfoService;
import com.gao.product.service.SpuSaleService;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService {
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private SpuImageService spuImageService;
    @Autowired
    private SpuSaleService spuSaleService;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Override
    public IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> page1, Long category3Id) {
        IPage<SpuInfo> page = ChainWrappers.lambdaQueryChain(spuInfoMapper).eq(SpuInfo::getCategory3Id, category3Id).orderByDesc(SpuInfo::getId)
                .select().page(page1);
        return page;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        spuInfoMapper.insert(spuInfo);
        List<SpuImage> list1 = spuInfo.getSpuImageList();
        if (!CollectionUtils.isEmpty(list1)) {
            for (SpuImage spuImage : list1) {
                spuImage.setSpuId(spuInfo.getId());
                spuImageService.save(spuImage);
            }
        }
        List<SpuSaleAttr> list2 = spuInfo.getSpuSaleAttrList();
        if (!CollectionUtils.isEmpty(list2)) {
            for (SpuSaleAttr spuSaleAttr : list2) {
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleService.save(spuSaleAttr);
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
    }
}
