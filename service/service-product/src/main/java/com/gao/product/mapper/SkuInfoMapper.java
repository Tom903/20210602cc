package com.gao.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.model.product.SkuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {

}
