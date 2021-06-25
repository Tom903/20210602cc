package com.gao.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.SpuInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {
    default IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> page1,  SpuInfo spuInfo) {
        return ChainWrappers.lambdaQueryChain(this).eq(SpuInfo::getCategory3Id,spuInfo.getCategory3Id()).orderByDesc(SpuInfo::getId)
                .select().page(page1);
    }
}
