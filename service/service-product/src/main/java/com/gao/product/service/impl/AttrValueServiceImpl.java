package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseAttrValue;
import com.gao.product.mapper.AttrValueMapper;
import com.gao.product.service.AttrValueService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, BaseAttrValue> implements AttrValueService {
    @Autowired
    private AttrValueMapper attrValueMapper;

    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        return ChainWrappers.lambdaQueryChain(attrValueMapper).eq(BaseAttrValue::getAttrId, attrId).list();
    }
}
