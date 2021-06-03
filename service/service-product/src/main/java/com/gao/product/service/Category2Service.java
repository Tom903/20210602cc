package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseCategory2;
import java.util.List;

public interface Category2Service extends IService<BaseCategory2> {

    List<BaseCategory2> getCategory2(Long category1Id);
}
