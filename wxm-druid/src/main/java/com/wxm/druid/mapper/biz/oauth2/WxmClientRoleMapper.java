package com.wxm.druid.mapper.biz.oauth2;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.oauth2.WxmClientRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * oauth_client_details 关联 wxm_c_role Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@DS("biz")
@Mapper
public interface WxmClientRoleMapper extends BaseMapper<WxmClientRole> {

}
