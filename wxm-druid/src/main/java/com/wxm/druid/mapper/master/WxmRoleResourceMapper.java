package com.wxm.druid.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.base.exception.BizSvcException;
import com.wxm.druid.entity.master.WxmRoleResource;
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
@Mapper
public interface WxmRoleResourceMapper extends BaseMapper<WxmRoleResource> {
    /**
     * 根据角色Id列表批量删除
     * @param list
     * @return
     */
    int deleteByRoleIds(@Param("list")List<Long>list);
}
