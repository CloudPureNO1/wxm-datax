package com.wxm.druid.mapper.master;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.master.WxmRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Mapper
@Repository
public interface WxmRoleMapper extends BaseMapper<WxmRole> {
    List<WxmRole> selectAll();
    List<WxmRole> selectByRoleId(@Param("roleId")Long roleId,@Param("roleStatus")String roleStatus);

    /**
     * 条件查询在用户组ID中的角色
     * @param roleId
     * @param roleName
     * @param roleCode
     * @param creator
     * @param roleType
     * @param roleStatus
     * @param groupId
     * @return
     */
    List<WxmRole> selectByCon(@Param("roleId")Long roleId, @Param("roleName") String roleName, @Param("roleCode")String roleCode,@Param("creator")String creator, @Param("roleType")String roleType, @Param("roleStatus")String roleStatus, @Param("groupId")Long groupId);;

    /**
     * 条件查询在用户组ID中的角色
     * @param roleId
     * @param roleName
     * @param roleCode
     * @param creator
     * @param roleType
     * @param roleStatus
     * @param groupId
     * @return
     */
    List<WxmRole> selectByConNonGroup(@Param("roleId")Long roleId, @Param("roleName") String roleName, @Param("roleCode")String roleCode,@Param("creator")String creator, @Param("roleType")String roleType, @Param("roleStatus")String roleStatus, @Param("groupId")Long groupId);;

    /**
     * 更加资源url查询对应的角色
     * @param resourceUrl
     * @return
     */
    @Select("select * from wxm_role r" +
            " where r.role_status='1'" +
            " and exists(" +
            "    select 1 from wxm_role_resource rrs,wxm_resource rs" +
            "    where r.role_id=rrs.role_id" +
            "    and rs.resource_status='1'" +
            "    and rs.resource_url=#{resourceUrl,jdbcType=VARCHAR}" +
            ")")
    List<WxmRole> selectByResourceUrl(@Param("resourceUrl")String resourceUrl);

    /**
     *  根据Api接口地址查询对应的角色
     * @param apiUrl
     * @return
     */
    @Select("select * from wxm_role r" +
            " where r.role_status='1'" +
            " and exists(" +
            "    select 1 from wxm_api a,wxm_role_api ra" +
            "    where r.role_id=ra.role_id" +
            "    and a.api_status='1'" +
            "    and a.api_url=#{apiUrl,jdbcType=VARCHAR}" +
            "    )")
    List<WxmRole> selectByApiUrl(@Param("apiUrl")String apiUrl);

    @Select("select * from wxm_role r ,wxm_group_role gr" +
            " where r.role_id = gr.role_id" +
            " and exists(" +
            "    select 1 from wxm_user u ,wxm_user_group ug" +
            "    where ug.group_id=gr.group_id" +
            "    and u.user_id=ug.user_id" +
            "    and u.username=#{username,jdbcType=VARCHAR}" +
            ")")
    List<WxmRole>findRolesByUsername(@Param("username") String username);
}
