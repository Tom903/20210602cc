package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseAttrValue;
import java.util.List;

public interface AttrValueService extends IService<BaseAttrValue> {


    List<BaseAttrValue> getAttrValueList(Long attrId);

}
