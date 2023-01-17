package com.wxm.service.db.master.oauth2.impl;

import com.wxm.druid.entity.master.oauth2.WxmCRole;
import com.wxm.druid.mapper.master.oauth2.WxmCRoleMapper;
import com.wxm.service.db.master.oauth2.IWxmCRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * oauth_client_details 客户端的角色 关联wxm_api 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Service
public class WxmCRoleServiceImpl extends ServiceImpl<WxmCRoleMapper, WxmCRole> implements IWxmCRoleService {

}
