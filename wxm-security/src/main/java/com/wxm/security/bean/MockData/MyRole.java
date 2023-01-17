package com.wxm.security.bean.MockData;


import com.wxm.base.exception.EncodeException;
import com.wxm.util.my.code.SM3Util;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/28 10:50
 * @since 1.0.0
 */
@Data
public class MyRole implements java.io.Serializable{
    private List<String>roleList=new ArrayList<>();

    public List<String> getRoleList(String url){
        if(url.contains("/auth/token")){
            roleList.add("MTMS_ROLE_TOKEN");
        }
        if(url.contains("/admin")){
            roleList.add("MTMS_ROLE_ADMIN");
        }
        if(url.contains("/manager")){
            roleList.add("MTMS_ROLE_ADMIN");
            roleList.add("MTMS_ROLE_MANAGER");
        }
        if(url.contains("/user")){
            roleList.add("MTMS_ROLE_ADMIN");
            roleList.add("MTMS_ROLE_MANAGER");
            roleList.add("MTMS_ROLE_USER");
        }
        return roleList;
    }


    public static void main(String [] args) throws EncodeException {
        System.out.println(SM3Util.encode("mtms-wxm-2021-secret"));
        System.out.println(UUID.randomUUID().toString());
    }
}
