package com.gao.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.common.execption.GmallException;
import com.gao.common.result.Result;
import com.gao.model.product.BaseAttrInfo;
import com.gao.model.product.BaseAttrValue;
import com.gao.model.product.BaseCategory1;
import com.gao.model.product.BaseCategory2;
import com.gao.model.product.BaseCategory3;
import com.gao.model.product.BaseSaleAttr;
import com.gao.model.product.BaseTrademark;
import com.gao.model.product.SkuInfo;
import com.gao.model.product.SpuImage;
import com.gao.model.product.SpuInfo;
import com.gao.model.product.SpuSaleAttr;
import com.gao.product.service.AttrInfoService;
import com.gao.product.service.AttrValueService;
import com.gao.product.service.Category2Service;
import com.gao.product.service.Category3Service;
import com.gao.product.service.ManageService;
import com.gao.product.service.SaleAttrService;
import com.gao.product.service.SkuInfoService;
import com.gao.product.service.SpuImageService;
import com.gao.product.service.SpuInfoService;
import com.gao.product.service.SpuSaleService;
import com.gao.product.service.TrademarkService;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台数据接口")
@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class BaseManageController {

    @Autowired
    private ManageService manageService;
    @Autowired
    private Category2Service category2Service;
    @Autowired
    private Category3Service category3Service;
    @Autowired
    private SpuInfoService spuInfoService;
    @Autowired
    private AttrInfoService attrInfoService;
    @Autowired
    private AttrValueService attrValueService;
    @Autowired
    private SaleAttrService saleAttrService;
    @Autowired
    private TrademarkService trademarkService;
    @Autowired
    private SpuImageService spuImageService;
    @Autowired
    private SpuSaleService spuSaleService;
    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 查询所有的一级分类数据：
     * http://api.gmall.com/admin/product/getCategory1
     */
    @GetMapping("getCategory1")
    public Result getCategory1() {
        List<BaseCategory1> list = manageService.getAll();
        return Result.ok(list);
    }

    /**
     * 查询所有二级分类数据
     * http://api.gmall.com/admin/product/getCategory2/{category1Id}
     *
     * @param category1Id
     * @return
     */

    @GetMapping("getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable Long category1Id) {
        List<BaseCategory2> list = category2Service.getCategory2(category1Id);
        return Result.ok(list);
    }

    /**
     * 查询所有三类数据
     * http://api.gmall.com/admin/product/getCategory3/{category2Id}
     *
     * @param category2Id
     * @return
     */
    @GetMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable Long category2Id) {
        List<BaseCategory3> list = category3Service.getCategory3(category2Id);
        return Result.ok(list);
    }

    /**
     * 根据分类Id 获取平台属性数据
     * 接口说明：
     * 1，平台属性可以挂在一级分类、二级分类和三级分类
     * 2，查询一级分类下面的平台属性，传：category1Id，0，0；   取出该分类的平台属性
     * 3，查询二级分类下面的平台属性，传：category1Id，category2Id，0；
     * 取出对应一级分类下面的平台属性与二级分类对应的平台属性
     * 4，查询三级分类下面的平台属性，传：category1Id，category2Id，category3Id；
     * 取出对应一级分类、二级分类与三级分类对应的平台属性
     * select * from
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable Long category1Id,
                               @PathVariable Long category2Id,
                               @PathVariable Long category3Id) {

        List<BaseAttrInfo> list = manageService.getattrInfo(category1Id, category2Id, category3Id);
        return Result.ok(list);
    }

    /**
     * 新增和修改
     * http://api.gmall.com/admin/product/saveAttrInfo{baseAttrInfo}
     *
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("saveAttrInfo/{baseAttrInfo}")
    public Result saveAttrInfo(@PathVariable BaseAttrInfo baseAttrInfo) {
        if (StringUtils.isEmpty(baseAttrInfo)) {
            throw new GmallException("请求参数为空", 100);
        }
        if (!StringUtils.isEmpty(baseAttrInfo.getId())) {
            attrInfoService.updateById(baseAttrInfo);
            return Result.ok();
        }
        attrInfoService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     * 根据平台属性ID获取平台属性对象数据
     * //http://api.gmall.com/admin/product/getAttrValueList/{attrId}
     *
     * @param attrId
     * @return
     */
    @GetMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId) {
        List<BaseAttrValue> list = attrValueService.getAttrValueList(attrId);
        return Result.ok(list);
    }

    /**
     * 根据查询条件封装控制器
     * springMVC 的时候，有个叫对象属性传值 如果页面提交过来的参数与实体类的参数一致，
     * 则可以使用实体类来接收数据
     * http://api.gmall.com/admin/product/1/10?category3Id=61
     *
     * @param page
     * @param size
     * @param category3Id
     * @return
     * @RequestBody 作用 将前台传递过来的json{"category3Id":"61"}  字符串变为java 对象。
     */
    @GetMapping("{page}/{size}/{category3Id}")
    public Result getSpuInfoPage(@PathVariable Long page,
                                 @PathVariable Long size,
                                 @PathVariable Long category3Id) {
        Page<SpuInfo> page1 = new Page<>(page, size);
        IPage<SpuInfo> list = spuInfoService.getSpuInfoPage(page1, category3Id);
        return Result.ok(list);
    }

    /**
     * 获取销售属性
     * http://api.gmall.com/admin/product/baseSaleAttrList
     *
     * @return
     */
    @GetMapping("baseSaleAttrList")
    public Result baseSaleAttrList() {
        List<BaseSaleAttr> list = saleAttrService.getList();
        return Result.ok(list);
    }


    /**
     * 获取品牌属性
     * http://api.gmall.com/admin/product/baseTrademark/getTrademarkList
     *
     * @return
     */
    @GetMapping("baseTrademark/getTrademarkList")
    public Result getTrademarkList() {
        List<BaseTrademark> list = trademarkService.getTrademarkList();
        return Result.ok(list);
    }

    /**
     * 添加spu
     * http://api.gmall.com/admin/product/saveSpuInfo/{spuInfo}
     *
     * @param spuInfo
     * @return
     */
    @PostMapping("saveSpuInfo/{spuInfo}")
    public Result saveSpuInfo(@PathVariable SpuInfo spuInfo) {
        if (StringUtils.isEmpty(spuInfo)) {
            throw new GmallException("请求参数为空", 100);
        }
        spuInfoService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    /**
     * 根据spuId获取图片列表
     * http://api.gmall.com/admin/product/spuImageList/{spuId}
     *
     * @param spuId
     * @return
     */
    @GetMapping("spuImageList/{spuId}")
    public Result spuImageList(@PathVariable Long spuId) {
        List<SpuImage> list = spuImageService.getList(spuId);
        return Result.ok(list);
    }


    /**
     * 根据spuId获取销售属性
     * http://api.gmall.com/admin/product/spuSaleAttrList/{spuId}
     *
     * @param spuId
     * @return
     */
    @GetMapping("spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable Long spuId) {
        List<SpuSaleAttr> list = spuSaleService.getlist(spuId);
        return Result.ok(list);
    }

    /**
     * 添加sku
     * http://api.gmall.com/admin/product/saveSkuInfo
     *
     * @param skuInfo
     * @return
     */
    @PostMapping("saveSkuInfo/{skuInfo}")
    public Result saveSkuInfo(@PathVariable SkuInfo skuInfo) {
        if (StringUtils.isEmpty(skuInfo)) {
            throw new GmallException("请求参数为空", 100);
        }
        skuInfoService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

    /**
     * 获取sku分页列表
     * http://api.gmall.com/admin/product/list/{page}/{limit}
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list/{page}/{limit}")
    public Result pageList(@PathVariable Long page,
                           @PathVariable Long limit) {
        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);
        IPage<SkuInfo> page1 = skuInfoService.pageList(skuInfoPage);
        return Result.ok(page1);
    }

    /**
     * http://api.gmall.com/admin/product/onSale/{skuId}
     * 上架
     *
     * @param skuId
     * @return
     */
    @GetMapping("onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId) {
        skuInfoService.onSale(skuId);
        return Result.ok();
    }


    /**
     * 下架
     * http://api.gmall.com/admin/product/cancelSale/{skuId}
     *
     * @param skuId
     * @return
     */
    @GetMapping("cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId) {
        skuInfoService.cancelSale(skuId);
        return Result.ok();
    }

    /**
     * 获取品牌分页列表
     * http://api.gmall.com/admin/product/baseTrademark/{page}/{limit}
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("baseTrademark/{page}/{limit}")
    public Result baseTrademark(@PathVariable Long page,
                                @PathVariable Long limit) {
        Page<BaseTrademark> p = new Page<>(page, limit);
        IPage<BaseTrademark> page1 = trademarkService.pageList(p);
        return Result.ok(page1);
    }

    /**
     * http://api.gmall.com/admin/product/baseTrademark/save
     * 添加品牌
     *
     * @param baseTrademark
     * @return
     */
    @PostMapping("baseTrademark/save/{baseTrademark}")
    public Result save(@PathVariable BaseTrademark baseTrademark) {
        if (StringUtils.isEmpty(baseTrademark)) {
            throw new GmallException("请求参数为空", 100);
        }
        trademarkService.save(baseTrademark);
        return Result.ok();
    }

    /**
     * http://api.gmall.com/admin/product/baseTrademark/update
     * 修改品牌
     */
    @PutMapping("baseTrademark/update/{baseTrademark}")
    public Result update(@PathVariable BaseTrademark baseTrademark) {
        if (StringUtils.isEmpty(baseTrademark)) {
            throw new GmallException("请求参数为空", 100);
        }
        trademarkService.updateBaseTrademark(baseTrademark);
        return Result.ok();
    }

    /**
     * 4、删除品牌
     * http://api.gmall.com/admin/product/baseTrademark/remove/{id}
     * @param id
     * @return
     */
    @DeleteMapping("baseTrademark/remove/{id}")
    public Result remove(@PathVariable Long id) {
        trademarkService.deleteById(id);
        return Result.ok();
    }

    /**
     * http://api.gmall.com/admin/product/baseTrademark/get/{id}
     * 根据Id获取品牌
     * @param id
     * @return
     */
    @GetMapping("baseTrademark/get/{id}")
    public Result getById(@PathVariable Long id) {
        BaseTrademark byId = trademarkService.getById(id);
        return Result.ok(byId);
    }




}
