package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * <p>
 * 角色-资源关联表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@DS("biz")
@Mapper
public interface WxmRoleResourceMapper extends BaseMapper<WxmRoleResource> {
    /**
     * 根据角色Id列表批量删除
     * @param list
     * @return
     */
    int deleteByRoleIds(@Param("list")List<Long>list);
}
