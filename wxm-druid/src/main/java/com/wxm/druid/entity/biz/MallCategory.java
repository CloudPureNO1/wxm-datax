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
 * 商品分类表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("mall_category")
public class MallCategory {

    /**
     * 分类ID
     */
    @TableId(value = "category_id", type = IdType.INPUT)
    private String categoryId;

    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 父分类ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 层级深度
     */
    @TableField("`level`")
    private Integer level;

    /**
     * 分类图片URL
     */
    @TableField("`category_image_url`")
    private Integer categoryImageUrl;


    /**
     * 分类图片资源ID
     */
    @TableField("`category_image_resource_id`")
    private Integer categoryImageResourceId;

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
