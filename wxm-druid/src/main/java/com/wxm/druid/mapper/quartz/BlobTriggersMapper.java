package com.wxm.druid.mapper.quartz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.quartz.BlobTriggers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * qrtz_blob_triggers表 用来存储Trigger作为Blob类型(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)。 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Repository
@DS("quartz")
@Mapper
public interface BlobTriggersMapper extends BaseMapper<BlobTriggers> {

}
