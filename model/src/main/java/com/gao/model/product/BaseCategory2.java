package com.gao.model.product;

import com.gao.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "商品二级分类")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_category2")
public class BaseCategory2 extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "二级分类名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "一级分类编号")
    @TableField("category1_id")
    private Long category1Id;

}
