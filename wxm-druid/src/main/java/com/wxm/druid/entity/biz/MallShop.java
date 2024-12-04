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
 * 店铺信息表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_shop")
public class MallShop {

    /**
     * 店铺ID
     */
    @TableId(value = "shop_id", type = IdType.INPUT)
    private String shopId;

    /**
     * 店主ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 店铺名称
     */
    @TableField("shop_name")
    private String shopName;

    /**
     * 店铺描述
     */
    @TableField("`description`")
    private String description;

    /**
     * 店铺Logo URL
     */
    @TableField("logo_url")
    private String logoUrl;

    /**
     * 店铺封面图片URL
     */
    @TableField("cover_image_url")
    private String coverImageUrl;

    /**
     * 店铺Logo资源ID
     */
    @TableField("logo_resource_id")
    private Integer logoResourceId;

    /**
     * 店铺封面图片资源ID
     */
    @TableField("cover_image_resource_id")
    private Integer coverImageResourceId;

    /**
     * 地理位置
     */
    @TableField("location")
    private String location;

    /**
     * 营业时间
     */
    @TableField("business_hours")
    private String businessHours;

    /**
     * 成立日期
     */
    @TableField("establishment_date")
    private LocalDate establishmentDate;

    /**
     * 店铺平均评分
     */
    @TableField("rating")
    private BigDecimal rating;

    /**
     * 评价数量
     */
    @TableField("review_count")
    private Integer reviewCount;

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
