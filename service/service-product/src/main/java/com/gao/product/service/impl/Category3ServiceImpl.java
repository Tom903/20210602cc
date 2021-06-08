package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseCategory3;
import com.gao.product.mapper.Category3Mapper;
import com.gao.product.service.Category3Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Category3ServiceImpl extends ServiceImpl<Category3Mapper, BaseCategory3> implements Category3Service {

    @Autowired
    Category3Mapper category3Mapper;
    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        List<BaseCategory3> list = ChainWrappers.lambdaQueryChain(category3Mapper).eq(BaseCategory3::getCategory2Id, category2Id).list();
        return list;
    }
}
