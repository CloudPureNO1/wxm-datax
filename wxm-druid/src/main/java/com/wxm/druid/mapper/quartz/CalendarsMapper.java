package com.wxm.druid.mapper.quartz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.Calendars;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * qrtz_calendars表 用来存储日历信息， quartz可配置一个日历来指定一个时间范围。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Repository
@DS("quartz")
@Mapper
public interface CalendarsMapper extends BaseMapper<Calendars> {

}
