package com.gao.product.api;

import com.gao.model.product.BaseCategoryView;
import com.gao.model.product.SkuInfo;
import com.gao.model.product.SpuSaleAttr;
import com.gao.product.service.CategoryViewService;
import com.gao.product.service.SkuInfoService;
import com.gao.product.service.SpuSaleService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
@EnableFeignClients
public class ProductApiController {
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private CategoryViewService categoryViewService;
    @Autowired
    private SpuSaleService spuSaleService;
    /**
     * 根据skuId获取sku信息
     * @param skuId
     * @return
     */
    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getAttrValueList(@PathVariable("skuId") Long skuId){
        return skuInfoService.getSkuInfo(skuId);
    }

    /**
     * 通过三级分类id查询分类信息
     *
     * @param category3Id
     * @return
     */
    @GetMapping("inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable Long category3Id) {
        return categoryViewService.getCategoryViewByCategory3Id(category3Id);
    }

    /**
     * 根据skuId 获取最新价格
     * @param skuId
     * @return
     */
    @GetMapping("inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable Long skuId){
        return skuInfoService.getSkuPrice(skuId);
    }

    /**
     * 根据spuId skuId 回显销售属性+属性值+锁定！
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable Long skuId,
                                                          @PathVariable Long spuId){
        return spuSaleService.getSpuSaleAttrListCheckBySku(skuId,spuId);
    }
}
