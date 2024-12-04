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
 * 商品信息表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_product")
public class MallProduct {

    /**
     * 商品ID
     */
    @TableId(value = "product_id", type = IdType.INPUT)
    private String productId;

    /**
     * 店铺ID
     */
    @TableField("shop_id")
    private String shopId;

    /**
     * 商品标题
     */
    @TableField("title")
    private String title;

    /**
     * 商品描述
     */
    @TableField("`description`")
    private String description;

    /**
     * 原价
     */
    @TableField("original_price")
    private BigDecimal originalPrice;

    /**
     * 折后价
     */
    @TableField("discounted_price")
    private BigDecimal discountedPrice;

    /**
     * 库存量
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 销量
     */
    @TableField("sold_count")
    private Integer soldCount;

    /**
     * 类别ID
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 主图URL
     */
    @TableField("main_image_url")
    private String mainImageUrl;

    /**
     * 商品主图资源ID
     */
    @TableField("main_image_resource_id")
    private Integer mainImageResourceId;

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
