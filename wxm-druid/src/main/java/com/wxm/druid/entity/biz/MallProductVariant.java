package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品变体表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_product_variant")
public class MallProductVariant {

    /**
     * 变体ID
     */
    @TableId(value = "variant_id", type = IdType.INPUT)
    private String variantId;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private String productId;

    /**
     * SKU
     */
    @TableField("sku")
    private String sku;

    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 尺寸
     */
    @TableField("size")
    private String size;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 库存
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 图片URL
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 变体图片资源ID
     */
    @TableField("image_resource_id")
    private Integer imageResourceId;

    /**
     * 最近一次操作者
     */
    @TableField("operator")
    private String operator;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
