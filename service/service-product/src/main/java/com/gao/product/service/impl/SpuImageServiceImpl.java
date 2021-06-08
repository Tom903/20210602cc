package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.SpuImage;
import com.gao.product.mapper.SpuImageMapper;
import com.gao.product.service.SpuImageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpuImageServiceImpl extends ServiceImpl<SpuImageMapper, SpuImage> implements SpuImageService {

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Override
    public List<SpuImage> getList(Long spuId) {
        List<SpuImage> list = ChainWrappers.lambdaQueryChain(spuImageMapper).eq(SpuImage::getSpuId, spuId).list();
        return list;
    }
}
