package com.wxm.druid.mapper.master;

import com.wxm.druid.entity.master.WxmRoleApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * <p>
 * 角色api 关联表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Mapper
public interface WxmRoleApiMapper extends BaseMapper<WxmRoleApi> {
    /**
     * 根据角色Id 和 接口id删除
     * @param roleId
     * @param list
     * @return
     */
    int deleteBatchByRoleIdAndApiIds(@Param("roleId")Long roleId,@Param("list")List<Long>list);

    /**
     * 根据接口ID列表删除
     * @param list
     * @return
     */
    int deleteByApiIds(@Param("list")List<Long>list);
}
