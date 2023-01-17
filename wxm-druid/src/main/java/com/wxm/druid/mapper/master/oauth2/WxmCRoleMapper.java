package com.wxm.druid.mapper.master.oauth2;

import com.wxm.druid.entity.master.oauth2.WxmCRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * oauth_client_details 客户端的角色 关联wxm_api Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Mapper
public interface WxmCRoleMapper extends BaseMapper<WxmCRole> {

}
