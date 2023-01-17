package com.wxm.service.db.master.oauth2.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.druid.entity.master.oauth2.OauthClientDetails;
import com.wxm.druid.mapper.master.oauth2.OauthClientDetailsMapper;
import com.wxm.service.db.master.oauth2.IOauthClientDetailsService;
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
