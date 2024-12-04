package com.wxm.druid.mapper.biz;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@DS("biz")
@Mapper
@Repository
public interface WxmResourceMapper extends BaseMapper<WxmResource> {

    /**
     * 查询所有资源
     * @return
     */
    List<WxmResource> selectAll();

    /**
     * 根据用户名查询有效资源
     * @param username
     * @return
     */
    List<WxmResource> selectByUsername(@Param("username")String username);

    /**
     * 根据角色ID查询对应的资源
     * @param roleId
     * @return
     */
    List<WxmResource> selectByRoleId(@Param("roleId")Long roleId);

    /**
     * 条件查询资源
     * @param resourceId
     * @param resourceName
     * @param resourceUrl
     * @param resourceType
     * @return
     */
    List<WxmResource> selectByCon(@Param("resourceId")Long resourceId,@Param("resourceName")String resourceName,@Param("resourceUrl")String resourceUrl,@Param("resourceType")String resourceType);
}
