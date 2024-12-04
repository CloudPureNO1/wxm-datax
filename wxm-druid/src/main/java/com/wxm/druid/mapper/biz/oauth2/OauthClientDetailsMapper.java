package com.wxm.druid.mapper.biz.oauth2;

import com.baomidou.dynamic.datasource.annotation.DS;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.biz.oauth2.OauthClientDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangsm
 * @since 2021-06-16
 */
@DS("biz")
@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {

}
