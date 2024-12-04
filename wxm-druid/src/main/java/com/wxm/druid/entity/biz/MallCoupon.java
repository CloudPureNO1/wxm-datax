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
 * 优惠券表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_coupon")
public class MallCoupon {

    /**
     * 优惠券ID
     */
    @TableId(value = "coupon_id", type = IdType.INPUT)
    private String couponId;

    /**
     * 优惠券代码
     */
    @TableField("`code`")
    private String code;

    /**
     * 关联促销活动ID
     */
    @TableField("promotion_id")
    private String  promotionId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 最小消费金额
     */
    @TableField("min_spend")
    private BigDecimal minSpend;

    /**
     * 优惠金额
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    /**
     * 使用次数限制
     */
    @TableField("use_count_limit")
    private Integer useCountLimit;

    /**
     * 已使用次数
     */
    @TableField("used_count")
    private Integer usedCount;

    /**
     * 有效起始时间
     */
    @TableField("valid_from")
    private LocalDateTime validFrom;

    /**
     * 有效结束时间
     */
    @TableField("valid_to")
    private LocalDateTime validTo;

    /**
     * 优惠券状态
     */
    @TableField("`status`")
    private String status;

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
