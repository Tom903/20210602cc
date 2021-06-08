package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.gao.model.product.BaseCategoryView;
import com.gao.product.mapper.CategoryViewMapper;
import com.gao.product.service.CategoryViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryViewServiceImpl extends ServiceImpl<CategoryViewMapper, BaseCategoryView> implements CategoryViewService {
    @Autowired
    private CategoryViewMapper categoryViewMapper;

    @Override
    public BaseCategoryView getCategoryViewByCategory3Id(Long category3Id) {
        return ChainWrappers.lambdaQueryChain(categoryViewMapper)
                .eq(BaseCategoryView::getCategory3Id, category3Id)
                .select().one();
    }
}
