package com.wxm.base.dto.mall.out.item;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 18:05
 * @since 1.0.0
 */
@Data
public class Item11001Out implements java.io.Serializable{

    /**
     * 店铺ID
     */

    private Long shopId;

    /**
     * 店主ID
     */

    private Integer userId;

    /**
     * 店铺名称
     */

    private String shopName;

    /**
     * 店铺描述
     */

    private String description;

    /**
     * 店铺Logo URL
     */

    private String logoUrl;

    /**
     * 店铺封面图片URL
     */

    private String coverImageUrl;

    /**
     * 店铺Logo资源ID
     */

    private Integer logoResourceId;

    /**
     * 店铺封面图片资源ID
     */

    private Integer coverImageResourceId;

    /**
     * 地理位置
     */

    private String location;

    /**
     * 营业时间
     */

    private String businessHours;

    /**
     * 成立日期
     */

    private LocalDate establishmentDate;

    /**
     * 店铺平均评分
     */

    private BigDecimal rating;

    /**
     * 评价数量
     */

    private Integer reviewCount;

    /**
     * 最近一次操作者
     */

    private String operator;

    /**
     * 创建者
     */

    private String creator;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 更新时间
     */

    private LocalDateTime updateTime;

}
