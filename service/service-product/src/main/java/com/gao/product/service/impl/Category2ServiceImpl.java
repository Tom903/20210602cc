package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseAttrInfo;
import com.gao.model.product.BaseCategory2;
import com.gao.product.mapper.Category2Mapper;
import com.gao.product.service.AttrInfoService;
import com.gao.product.service.Category2Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Category2ServiceImpl extends ServiceImpl<Category2Mapper, BaseCategory2> implements Category2Service {
    @Autowired
    private Category2Mapper category2Mapper;
    @Autowired
    private AttrInfoService attrInfoService;
    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        List<BaseCategory2> list = ChainWrappers.lambdaQueryChain(category2Mapper)
                .eq(BaseCategory2::getCategory1Id, category1Id)
                .list();
        return list;
    }
}
