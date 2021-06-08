package com.gao.item.service.impl;

import com.gao.item.service.ItemService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    //  数据汇总
    //  @Autowired
    //  private ProductFeignClient productFeignClient;
    @Override
    public Map<String, Object> getItem(Long skuId) {
        Map<String, Object> map = new HashMap<>();
        //  分类数据的值 =  productFeignClient.get分类数据的方法
        //  map.put("分类数据的key","分类数据的值");

        return map;
    }
}
