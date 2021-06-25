package com.gao.product.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseAttrValue;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttrValueMapper extends BaseMapper<BaseAttrValue> {

    default int deletecc(Long id) {
        return this.delete(new QueryWrapper<BaseAttrValue>().eq("attr_id", id));
    }
}