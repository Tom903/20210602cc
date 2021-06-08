package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseSaleAttr;
import com.gao.product.mapper.SaleAttrMapper;
import com.gao.product.service.SaleAttrService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleAttrServiceImpl extends ServiceImpl<SaleAttrMapper, BaseSaleAttr> implements SaleAttrService {
    @Autowired
    private SaleAttrMapper saleAttrMapper;
    @Override
    public List<BaseSaleAttr> getList() {
        List<BaseSaleAttr> list = ChainWrappers.lambdaQueryChain(saleAttrMapper).list();
        return list;
    }
}
