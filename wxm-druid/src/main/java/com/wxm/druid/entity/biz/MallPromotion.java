package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 促销活动表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_promotion")
public class MallPromotion {

    /**
     * 促销活动ID
     */
    @TableId(value = "promotion_id", type = IdType.INPUT)
    private String promotionId;

    /**
     * 活动名称
     */
    @TableField("promotion_name")
    private String promotionName;

    /**
     * 活动描述
     */
    @TableField("`description`")
    private String description;

    /**
     * 促销类型
     */
    @TableField("`type`")
    private String type;

    /**
     * 优惠值（百分比或固定金额）
     */
    @TableField("`value`")
    private BigDecimal value;

    /**
     * 开始日期
     */
    @TableField("start_date")
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @TableField("end_date")
    private LocalDate endDate;

    /**
     * 是否适用于所有商品,预留，采用第三张关系表
     */
    @TableField("apply_to_all")
    private Boolean applyToAll;

    /**
     * 特定商品ID列表，逗号分隔，当apply_to_all为FALSE时使用，预留，采用第三张关系表
     */
    @TableField("product_ids")
    private String productIds;

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
