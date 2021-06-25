package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.model.product.SkuSaleAttrValue;
import com.gao.product.mapper.SkuSaleAttrValueMapper;
import com.gao.product.service.SkuSaleAttrValuesService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SkuSaleAttrValuesServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValue> implements SkuSaleAttrValuesService {
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public Map getSkuValueIdsMap(Long spuId) {
        HashMap<Object, Object> map = new HashMap<>();
        List<Map> list = skuSaleAttrValueMapper.getList(spuId);
        if (!CollectionUtils.isEmpty(list)) {
            for (Map map1 : list) {
                map.put(map1.get("value_ids"), map1.get("sku_id"));
            }
        }
        return map;
    }
}
