package com.wxm.service.db.biz.oauth2.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.druid.entity.biz.oauth2.OauthClientDetails;
import com.wxm.druid.mapper.biz.oauth2.OauthClientDetailsMapper;
import com.wxm.service.db.biz.oauth2.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangsm
 * @since 2021-06-16
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

}
