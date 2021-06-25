package com.gao.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseAttrInfo;
import com.gao.model.product.BaseAttrValue;
import com.gao.product.mapper.AttrInfoMapper;
import com.gao.product.mapper.AttrValueMapper;
import com.gao.product.service.AttrInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class AttrInfoServiceImpl extends ServiceImpl<AttrInfoMapper, BaseAttrInfo> implements AttrInfoService {

    @Autowired
    private AttrInfoMapper attrInfoMapper;
    @Autowired
    private AttrValueMapper attrValueMapper;

    @Override
    @Transactional
    public Integer saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //  baseAttrInfo
        if (StringUtils.isEmpty(baseAttrInfo.getId())){
            // 保存
            attrInfoMapper.insert(baseAttrInfo);
        }else {
            //  修改
            attrInfoMapper.updateById(baseAttrInfo);
        }
        //先刪除在插入

        int deletecc = attrValueMapper.deletecc(baseAttrInfo.getId());
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (!CollectionUtils.isEmpty(attrValueList)) {
            for (BaseAttrValue attrValue : attrValueList) {
                attrValue.setAttrId(baseAttrInfo.getId());
                attrValueMapper.insert(attrValue);
            }
        }
        return null;
    }

}
