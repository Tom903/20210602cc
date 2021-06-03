package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseCategory3;
import java.util.List;

public interface Category3Service extends IService<BaseCategory3> {

    List<BaseCategory3> getCategory3(Long category2Id);
}
