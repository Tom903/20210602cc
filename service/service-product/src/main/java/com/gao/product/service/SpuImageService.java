package com.gao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.model.product.SpuImage;
import java.util.List;

public interface SpuImageService extends IService<SpuImage> {

    List<SpuImage> getList(Long spuId);


}
