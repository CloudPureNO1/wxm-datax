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
 * 订单信息表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_order")
public class MallOrder {

    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private String orderId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String  userId;

    /**
     * 地址ID
     */
    @TableField("address_id")
    private String  addressId;

    /**
     * 店铺ID
     */
    @TableField("shop_id")
    private String shopId;

    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 总金额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 运费
     */
    @TableField("shipping_fee")
    private BigDecimal shippingFee;

    /**
     * 折扣金额
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    /**
     * 支付方式
     */
    @TableField("payment_method")
    private String paymentMethod;

    /**
     * 下单时间
     */
    @TableField("order_time")
    private LocalDateTime orderTime;

    /**
     * 预计送达时间
     */
    @TableField("estimated_delivery")
    private LocalDate estimatedDelivery;

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
