package com.wxm.security.oauth2.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>可以通过这个方法跳转token信息，也可以在controller 中重新写一个/oauth/token 接口，替代默认的</p>
 * <p>我们在请求token后，前端如果有需求，比如说要将用户信息显示在页面上，那么请求token的时候能不能给它添加一些额外参数呢？答案是肯定的，也比较简单，只需要实现TokenEnhancer接口就可以了，</p>
 *
 * @author 王森明
 * @date 2021/4/29 17:21
 * @since 1.0.0
 */
@Service("mytTokenEnhancer")
public class MytTokenEnhancerImpl implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // grant_type:client_credentials  客户端模式时，没有用户名和密码,
        // authentication.getPrincipal(); 的值为client_id
        /**
         * 源码
         * 	public Object getPrincipal() {
         * 		return this.userAuthentication == null ? this.storedRequest.getClientId() : this.userAuthentication
         * 				.getPrincipal();
         *        }
         */
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        String client_id=authentication.getOAuth2Request().getClientId();
        // mtms-SPI-wxm-0001  SPI 标识的是外部接入方，不需要用户名和密码
        // 外部第三方接入，不需要传回账号信息和其他菜单等信息
        // 不在这里判断，放入TokenController返回token的时候判断
        Map<String, Object> map = new LinkedHashMap<>();
        // 设置账号信息
        if(authentication.getPrincipal() instanceof String){
            map.put("client_id",authentication.getPrincipal());
        }else{
            UserDetails account = (UserDetails) authentication.getPrincipal();
            map.put("account",account);
        }
        token.setAdditionalInformation(map);
        return token;
    }


}
