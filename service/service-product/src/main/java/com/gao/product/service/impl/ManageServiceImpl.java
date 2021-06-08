package com.gao.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.model.product.BaseAttrInfo;
import com.gao.model.product.BaseCategory1;
import com.gao.model.product.BaseCategoryView;
import com.gao.model.product.SkuInfo;
import com.gao.product.mapper.ManageMapper;
import com.gao.product.service.ManageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl extends ServiceImpl<ManageMapper, BaseCategory1> implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public List<BaseCategory1> getAll() {
        List<BaseCategory1> list = manageMapper.selectList(null);
        return list;
    }

    @Override
    public List<BaseAttrInfo> getattrInfo(Long category1Id, Long category2Id, Long category3Id) {
        List<BaseAttrInfo> list = manageMapper.getattrInfo(category1Id, category2Id, category3Id);
        return list;
    }


}
