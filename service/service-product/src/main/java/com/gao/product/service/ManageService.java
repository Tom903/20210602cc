package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.BaseCategory1;
import com.gao.model.product.BaseCategory2;
import java.util.List;

public interface ManageService extends IService<BaseCategory1> {

    List<BaseCategory1> getAll();

}
