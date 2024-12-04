package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.WxmPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 资源的权限表（资源中的按钮和其他功能） Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@DS("biz")
@Mapper
@Repository
public interface WxmPermissionMapper extends BaseMapper<WxmPermission> {
    /**
     * 无条件查询所有列表
     * @return
     */
    List<WxmPermission> selectAll();

    /**
     * 条件查询权限列表，在角色ID中的权限
     * @param permissionId
     * @param permissionName
     * @param permissionCode
     * @param creator
     * @param permissionType
     * @param permissionStatus
     * @param roleId
     * @return
     */
    List<WxmPermission> selectByCon(@Param("permissionId")Long permissionId,@Param("permissionName")String permissionName,@Param("permissionCode")String permissionCode,@Param("creator")String creator,@Param("permissionType")String permissionType,@Param("permissionStatus")String permissionStatus,@Param("roleId")Long roleId);

    /**
     * 条件查询权限列表，不在角色ID中的权限
     * @param permissionId
     * @param permissionName
     * @param permissionCode
     * @param creator
     * @param permissionType
     * @param permissionStatus
     * @param resourceId
     * @param roleId
     * @return
     */
    List<WxmPermission> selectByConNonRole(@Param("permissionId")Long permissionId,@Param("permissionName")String permissionName,@Param("permissionCode")String permissionCode,@Param("creator")String creator,@Param("permissionType")String permissionType,@Param("permissionStatus")String permissionStatus,@Param("resourceId")Long resourceId,@Param("roleId")Long roleId);

    /**
     * 查询所有权限信息，包含是否授权给角色，角色为空时，是否授权全部为否0     rbac51005
     * @param permissionId
     * @param permissionName
     * @param permissionCode
     * @param roleId
     * @return
     */
    List<WxmPermission>selectByConAboutRole(@Param("permissionId")Long permissionId,@Param("permissionName")String permissionName,@Param("permissionCode")String permissionCode,@Param("roleId")Long roleId);
}
