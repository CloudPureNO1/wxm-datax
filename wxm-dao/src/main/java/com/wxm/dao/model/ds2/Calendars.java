package com.wxm.dao.model.ds2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

/**
 * <p>
 * qrtz_calendars表 用来存储日历信息， quartz可配置一个日历来指定一个时间范围。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("qrtz_calendars")
public class Calendars {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 日历名称
     */
    @TableField(value = "CALENDAR_NAME")
    @MppMultiId
    private String calendarName;

    /**
     * 一个blob字段，存放持久化calendar对象
     */
    @TableField("CALENDAR")
    private Blob calendar;


}
