package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseAttrInfo;
import com.gao.model.product.BaseAttrValue;
import com.gao.product.mapper.AttrInfoMapper;
import com.gao.product.service.AttrInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrInfoServiceImpl extends ServiceImpl<AttrInfoMapper, BaseAttrInfo> implements AttrInfoService {

    @Autowired
    private AttrInfoMapper attrInfoMapper;

    @Override
    public Integer saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        return attrInfoMapper.insert(baseAttrInfo);
    }


}
