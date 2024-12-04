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
 * 评价表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_review")
public class MallReview {

    /**
     * 评价ID
     */
    @TableId(value = "review_id", type = IdType.INPUT)
    private String reviewId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private String productId;

    /**
     * 评分（1-5）
     */
    @TableField("rating")
    private Boolean rating;

    /**
     * 评论内容
     */
    @TableField("`comment`")
    private String comment;

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
