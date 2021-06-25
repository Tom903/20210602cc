package com.gao.product.client;

import com.gao.model.product.BaseCategoryView;
import com.gao.model.product.SkuInfo;
import com.gao.model.product.SpuSaleAttr;
import com.gao.product.client.impl.ProductDegradeFeignClient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-product", fallback = ProductDegradeFeignClient.class)
public interface ProductFeignClient {

    /**
     * 通过三级分类id查询分类信息
     * @param category3Id
     * @return
     */
    @GetMapping("api/product/inner/getCategoryView/{category3Id}")
    BaseCategoryView getCategoryView(@PathVariable Long category3Id);

    /**
     * 根据skuId获取最新价格
     * @param skuId
     * @return
     */
    @GetMapping("api/product/inner/getSkuPrice/{skuId}")
    BigDecimal getSkuPrice(@PathVariable Long skuId);

    /**
     * 根据spuId skuId 回显销售属性+属性值+锁定！
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("api/product/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable Long skuId, @PathVariable Long spuId);

    /**
     * 根据spuId 查询map 集合属性
     * @param spuId
     * @return
     */
    @GetMapping("api/product/inner/getSkuValueIdsMap/{spuId}")
    Map getSkuValueIdsMap(@PathVariable Long spuId);

    /**
     * 根据skuId获取sku信息
     * @param skuId
     * @return
     */
    @GetMapping("api/product/inner/getSkuInfo/{skuId}")
    SkuInfo getSkuInfo(@PathVariable Long skuId);
}
