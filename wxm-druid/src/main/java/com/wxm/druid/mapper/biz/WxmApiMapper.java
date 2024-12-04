package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.WxmApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 接口资源api Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@DS("biz")
@Mapper
public interface WxmApiMapper extends BaseMapper<WxmApi> {
    /**
     * 条件查询接口，如果roleId 不为空，则查询roleId的接口
     * @param apiId
     * @param apiTitle
     * @param apiCode
     * @param apiUrl
     * @param creator
     * @param roleId
     * @return
     */
    List<WxmApi>selectByCon(@Param("apiId")Long apiId,@Param("apiTitle")String apiTitle,@Param("apiCode")String apiCode,@Param("apiUrl")String apiUrl,@Param("creator")String creator,@Param("apiStatus")String apiStatus,@Param("roleId")Long roleId);
    /**
     * 条件查询接口，如果roleId 不为空，则不查询roleId的接口
     * @param apiId
     * @param apiTitle
     * @param apiCode
     * @param apiUrl
     * @param creator
     * @param roleId
     * @return
     */
    List<WxmApi>selectByConNonRole(@Param("apiId")Long apiId,@Param("apiTitle")String apiTitle,@Param("apiCode")String apiCode,@Param("apiUrl")String apiUrl,@Param("creator")String creator,@Param("apiStatus")String apiStatus,@Param("roleId")Long roleId);

    /**
     * 根据用户名查询接口
     * @param username
     * @return
     */
    List<WxmApi>selectByUsername(@Param("username")String username);

    /**
     * 查询所有接口信息，包含是否授权给角色，角色为空时，是否授权全部为否0
     * @param apiId
     * @param apiTitle
     * @param roleId
     * @return
     */
    List<WxmApi>selectByConAboutRole(@Param("apiId")Long apiId,@Param("apiTitle")String apiTitle,@Param("roleId")Long roleId);

}
