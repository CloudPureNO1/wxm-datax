package com.wxm.security.oauth2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxm.base.dto.DataRtn;
import com.wxm.druid.entity.master.WxmUser;
import com.wxm.security.oauth2.dto.UserInfo;
import com.wxm.service.db.master.impl.WxmResourceService;
import com.wxm.service.db.master.impl.WxmUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>获取token</p>
 * <p>外部接口调用</p>
 *
 * @author 王森明
 * @date 2021/6/15 11:48
 * @since 1.0.0
 */
@Slf4j
@RestController
public class TokenController {
    private final TokenEndpoint tokenEndpoint;
    private final WxmUserService userService;
    private final WxmResourceService resourceService;

    public TokenController(TokenEndpoint tokenEndpoint, WxmUserService userService, WxmResourceService resourceService) {
        this.tokenEndpoint = tokenEndpoint;
        this.userService = userService;
        this.resourceService = resourceService;
    }

    /**
     * 重写令牌申请接口,返回自定义数据格式
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("/oauth/token")
    public DataRtn postAccessTokenWithUserInfo(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        /**
         * {
         *     "access_token": "3932f9f2-175b-439d-b140-6ce0806a37df",
         *     "token_type": "bearer",
         *     "refresh_token": "93e2209a-53a6-45ac-8183-48c40086668a",
         *     "expires_in": 14399,  --有效时间秒
         *     "scope": "read"
         * }
         *
         * 替换成
         * {
         * "access_token":"cae9aca7-3d2f-4ea5-ad34-0d391e64fef2",
         * "expiration":1606911117013,                               -- 失效时间  毫秒
         * "token_type":"bearer",
         * "refresh_token":null,
         * "scope":[
         * "read",
         * "write"
         * ]
         * }
         */

        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Map<String, Object> data = new LinkedHashMap();
        data.put("access_token", accessToken.getValue());
        data.put("token_type", accessToken.getTokenType());
        // data.put("refresh_token", accessToken.getRefreshToken());
        data.put("expiration", accessToken.getExpiresIn() * 1000 + System.currentTimeMillis());
        data.put("scope", accessToken.getScope());
/*        data.put("additionalInformation", accessToken.getAdditionalInformation());
        "additionalInformation": {
            "acount": {
                "username": "admin",
                        "authorities": [
                {
                    "authority": "manager"
                }
                ],
                "accountNonExpired": true,
                        "accountNonLocked": true,
                        "credentialsNonExpired": true,
                        "enabled": true
            }
        },*/

        if (accessToken.getRefreshToken() != null) {
            data.put("refreshToken", accessToken.getRefreshToken().getValue());
        }

        // mtms-SPI-wxm-0001  SPI 标识的是外部接入方，不需要用户名和密码
        // 外部第三方接入，不需要传回账号信息和其他菜单等信息
        // 内部用户客户端：
        String clientId = principal.getName();
        boolean isOutApi=Arrays.stream(clientId.split("-")).anyMatch(item->item.equals("SPI"));
        //
        boolean isEmptyUsername=(parameters==null||parameters.isEmpty()||parameters.get("username")==null|| StringUtils.hasLength(parameters.get("username").toString()));
/*        if(!isEmptyUsername&&!isOutApi){
            data =this.loadData(data,accessToken);
        }*/
        if(!isEmptyUsername&&!isOutApi){
            data =this.loadDataWithParams(data,accessToken,parameters,isEmptyUsername);
        }
        return new DataRtn().success().data(data);
    }



    private Map<String,Object> loadDataWithParams(Map<String,Object> data,OAuth2AccessToken accessToken,Map<String, String> parameters,boolean isEmptyUsername){
        try{
            //内部用户如果不传入用户名和密码，则也不加载
            //用户数据
            User user= (User) accessToken.getAdditionalInformation().get("acount");
            UserInfo userInfo= new UserInfo();
            QueryWrapper<WxmUser> queryWrapper=new QueryWrapper();
            String username=null;
            if(isEmptyUsername) {return data;}
            if(user == null) {
                username=parameters.get("username").toString();
            }

            WxmUser userModel=userService.getOne(queryWrapper.eq("username",username));
            BeanUtils.copyProperties(userModel,userInfo);
            if(user!=null&&!CollectionUtils.isEmpty(user.getAuthorities())){
                userInfo.setUserType(user.getAuthorities().stream().map(authority ->authority.getAuthority()).collect(Collectors.joining(",")));
            }
            data.put("userInfo",userInfo);
            // 导航栏数据,登录后不应该立即给出导航菜单数据
/*            List<WxmResource> resourceList=resourceService.findByUserId(userModel.getUserId());
            data.put("navData",resourceList);*/
            return data;
        }catch (Exception e){
            log.info("加载数据异常：{}",e.getMessage(),e);
            throw new RuntimeException("加载数据异常："+e.getMessage());
        }
    }

    private Map<String,Object> loadData(Map<String,Object> data,OAuth2AccessToken accessToken){
        try{
            //内部用户如果不传入用户名和密码，则也不加载
            //用户数据
            User user= (User) accessToken.getAdditionalInformation().get("acount");
            if(user == null) {return data;}
            QueryWrapper<WxmUser> queryWrapper=new QueryWrapper();
            WxmUser userModel=userService.getOne(queryWrapper.eq("username",user.getUsername()));
            UserInfo userInfo= new UserInfo();
            BeanUtils.copyProperties(userModel,userInfo);
            if(!CollectionUtils.isEmpty(user.getAuthorities())){
                userInfo.setUserType(user.getAuthorities().stream().map(authority ->authority.getAuthority()).collect(Collectors.joining(",")));
            }
            data.put("userInfo",userInfo);

            // 导航栏数据,登录后不应该立即给出导航菜单数据
    /*        List<WxmResource> resourceList=resourceService.findByUserId(userModel.getUserId());
            data.put("navData",resourceList);*/
            return data;
        }catch (Exception e){
            log.info("加载数据异常：{}",e.getMessage(),e);
            throw new RuntimeException("加载数据异常："+e.getMessage());
        }
    }


}
