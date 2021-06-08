package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseCategoryView;

public interface CategoryViewService extends IService<BaseCategoryView> {

    BaseCategoryView getCategoryViewByCategory3Id(Long category3Id);

}
