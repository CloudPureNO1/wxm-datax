package com.wxm.service.db.master.oauth2.impl;

import com.wxm.druid.entity.master.oauth2.WxmClientRole;
import com.wxm.druid.mapper.master.oauth2.WxmClientRoleMapper;
import com.wxm.service.db.master.oauth2.IWxmClientRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * oauth_client_details 关联 wxm_c_role 服务实现类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Service
public class WxmClientRoleServiceImpl extends ServiceImpl<WxmClientRoleMapper, WxmClientRole> implements IWxmClientRoleService {

}
