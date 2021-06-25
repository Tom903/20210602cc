package com.gao.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.model.product.SkuSaleAttrValue;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {

    List<Map> getList(Long spuId);

}
