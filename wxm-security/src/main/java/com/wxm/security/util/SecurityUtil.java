package com.wxm.security.util;

import com.wxm.security.bean.Token;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/27 16:32
 * @since 1.0.0
 */
public class SecurityUtil {
    public  static boolean isAllowSecurityEnabled(){
/*
        SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
*/

        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            return true;
        } else {
            if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public  static  boolean isEmptyToken(Token token){
        if(token==null){
            return true;
        }
        if(!StringUtils.hasLength(token.getAccessToken())){
            return true;
        }
        if(token.getExpiration()==0L){
            return true;
        }
        return false;
    }
}
