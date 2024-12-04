package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Getter
@Setter
@TableName("wxm_dictionary")
public class WxmDictionary {

    /**
     * 主键
     */
    @TableId(value = "dict_id", type = IdType.INPUT)
    private String dictId;

    /**
     * 字典类别
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 字典值
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 字典中文名称
     */
    @TableField("dict_label")
    private String dictLabel;

    /**
     * 0 无效 1有效
     */
    @TableField("dict_status")
    private String dictStatus;

    /**
     * 父字典值
     */
    @TableField("dict_parent_value")
    private String dictParentValue;


    @TableField("order_num")
    private Integer orderNum;
}
