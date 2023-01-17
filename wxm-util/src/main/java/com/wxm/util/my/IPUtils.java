package com.wxm.util.my;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/14 15:06
 * @since 1.0.0
 */
public class IPUtils {
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
    public static String getSystemType(){
        String osName=System.getProperty("os.name");
        if(StringUtils.hasLength(osName)){
            if(osName.toLowerCase().indexOf("linux")!=-1){
                return "linux";
            }
            if(osName.toLowerCase().indexOf("windows")!=-1){
                return "windows";
            }
            if(osName.toLowerCase().indexOf("mac")!=-1){
                return "mac";
            }
            return "others";
        }
        return "linux";
    }


}
