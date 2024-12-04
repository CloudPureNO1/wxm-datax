package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色-权限关联表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@DS("biz")
@Mapper
@Repository
public interface WxmRolePermissionMapper extends BaseMapper<WxmRolePermission> {
    /**
     * 根据角色Id,权限Id列表删除
     * @param roleId
     * @param list
     * @return
     */
    int deleteBatchByRoleIdAndPermissionIds(@Param("roleId")Long roleId, @Param("list") List<Long> list);

    /**
     * 根据角色Id列表批量删除
     * @param list
     * @return
     */
    int deleteByRoleIds(@Param("list")List<Long>list);

    /**
     * 根据权限ID列表删除
     * @param list
     * @return
     */
    int deleteByPermissionIds(@Param("list")List<Long>list);


}
