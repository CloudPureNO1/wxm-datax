package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmGroupRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>
 * 用户组-角色关联表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@DS("biz")
@Mapper
@Repository
public interface WxmGroupRoleMapper extends BaseMapper<WxmGroupRole> {
    /**
     * 根据用户组Id,和角色Id列表，删除
     * @param groupId
     * @param list
     * @return
     */
    int deleteByGroupIdAndRoleIds(@Param("groupId")Long groupId,@Param("list")List<Long>list);

    /**
     * 根据用户组Id列表批量删除
     * @param list
     * @return
     */
    int deleteByGroupIds(@Param("list")List<Long>list);

    /**
     * 根据角色Id列表批量删除
     * @param list
     * @return
     */
    int deleteByRoleIds(@Param("list")List<Long>list);
}
