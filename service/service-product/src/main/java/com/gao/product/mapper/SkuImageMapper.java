package com.gao.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.SkuImage;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SkuImageMapper extends BaseMapper<SkuImage> {

    default List<SkuImage> selectOne(@Param("skuId") Long skuId){
        return ChainWrappers.lambdaQueryChain(this)
                .eq(SkuImage::getSkuId, skuId)
                .select().list();

    }
}
