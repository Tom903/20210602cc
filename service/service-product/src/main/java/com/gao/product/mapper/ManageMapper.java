package com.gao.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.model.product.BaseAttrInfo;
import com.gao.model.product.BaseCategory1;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManageMapper extends BaseMapper<BaseCategory1> {

    List<BaseAttrInfo> getattrInfo(@Param("category1Id") Long category1Id,
                                   @Param("category2Id") Long category2Id,
                                   @Param("category3Id") Long category3Id);

}
