package com.wxm.service.db.biz.oauth2.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.oauth2.WxmCRole;
import com.wxm.druid.mapper.biz.oauth2.WxmCRoleMapper;
import com.wxm.service.db.biz.oauth2.IWxmCRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * oauth_client_details 客户端的角色 关联wxm_api 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@DS("biz")
public class WxmCRoleServiceImpl extends ServiceImpl<WxmCRoleMapper, WxmCRole> implements IWxmCRoleService {

}
