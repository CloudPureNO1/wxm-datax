package com.wxm.service.db.biz.oauth2;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.oauth2.WxmClientRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * oauth_client_details 关联 wxm_c_role 服务类
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@DS("biz")
public interface IWxmClientRoleService extends IService<WxmClientRole> {

}
