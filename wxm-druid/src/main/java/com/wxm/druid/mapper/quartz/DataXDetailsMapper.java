package com.wxm.druid.mapper.quartz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.DataXDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * datax 定时任务文件信息 Mapper 接口
 * </p>
 *
 * @author 王森名
 * @since 2022-07-06
 */
@Repository
@DS("quartz")
@Mapper
public interface DataXDetailsMapper extends BaseMapper<DataXDetails> {

}
