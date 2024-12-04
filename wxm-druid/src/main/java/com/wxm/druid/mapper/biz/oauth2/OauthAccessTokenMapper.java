package com.wxm.druid.mapper.biz.oauth2;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.oauth2.OauthAccessToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * OAuth 2.0 访问令牌 Mapper 接口
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@DS("biz")
@Mapper
public interface OauthAccessTokenMapper extends BaseMapper<OauthAccessToken> {

}
