package com.gao.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.SpuSaleAttrValue;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpuSaleAttrValueMapper extends BaseMapper<SpuSaleAttrValue> {
    default List<SpuSaleAttrValue> getList(Long spuId){
        return ChainWrappers.lambdaQueryChain(this)
                .eq(SpuSaleAttrValue::getSpuId, spuId)
                .select().list();
    }

}
