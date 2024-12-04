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
 * 收货地址表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_address")
public class MallAddress {

    /**
     * 地址ID
     */
    @TableId(value = "address_id", type = IdType.INPUT)
    private String addressId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 收件人姓名
     */
    @TableField("receiver_name")
    private String receiverName;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 省份
     */
    @TableField("province")
    private String province;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 区县
     */
    @TableField("district")
    private String district;

    /**
     * 详细地址
     */
    @TableField("address_line")
    private String addressLine;

    /**
     * 是否默认地址
     */
    @TableField("is_default")
    private Boolean isDefault;

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
