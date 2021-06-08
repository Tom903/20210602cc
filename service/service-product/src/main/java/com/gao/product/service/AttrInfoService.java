package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseAttrInfo;

public interface AttrInfoService extends IService<BaseAttrInfo> {
    Integer saveAttrInfo(BaseAttrInfo baseAttrInfo);

}
