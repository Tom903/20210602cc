package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseAttrInfo;
import com.gao.model.product.BaseCategory1;
import com.gao.model.product.BaseCategory2;
import com.gao.model.product.BaseCategoryView;
import com.gao.model.product.SkuInfo;
import java.util.List;

public interface ManageService extends IService<BaseCategory1> {

    List<BaseCategory1> getAll();

    List<BaseAttrInfo> getattrInfo(Long category1Id, Long category2Id, Long category3Id);

}
