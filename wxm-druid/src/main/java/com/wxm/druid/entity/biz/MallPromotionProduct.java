package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 促销活动与商品/变体关联表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_promotion_product")
public class MallPromotionProduct {

    /**
     * 映射ID
     */
    @TableId(value = "mapping_id", type = IdType.INPUT)
    private String mappingId;

    /**
     * 促销活动ID
     */
    @TableField("promotion_id")
    private String promotionId;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private String productId;

    /**
     * 变体ID
     */
    @TableField("variant_id")
    private String variantId;

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
