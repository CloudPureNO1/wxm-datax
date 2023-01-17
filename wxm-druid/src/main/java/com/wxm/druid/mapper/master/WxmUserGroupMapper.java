package com.wxm.druid.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.master.WxmUserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户-用户组关联表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Mapper
@Repository
public interface WxmUserGroupMapper extends BaseMapper<WxmUserGroup> {
    /**
     * 根据用户组Id,和用户Id列表，批量删除
     *
     * @param groupId
     * @param list
     * @return
     */
    int deleteBatchByGroupIdAndUserIds(@Param("groupId") Long groupId, @Param("list") List<Long> list);

    /**
     * 根据用户ID列表批量删除
     * @param list
     * @return
     */
    int deleteBatchByUserIds(@Param("list") List<Long> list);

    /**
     * 根据用户组ID列表批量删除
     * @param list
     * @return
     */
    int deleteBatchByGroupIds(@Param("list")List<Long>list);
}
