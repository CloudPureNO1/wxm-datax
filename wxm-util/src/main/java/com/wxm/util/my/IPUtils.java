package com.wxm.util.my;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/14 15:06
 * @since 1.0.0
 */
public class IPUtils {

    /**
     * 获取服务器（部署应用）的IP
     * 多网卡
     * <p>
     * 在列出的IP地址中，包含了本地回环地址（127.0.0.1 和 ::1），链路本地地址（以 fe80:: 开头的IPv6地址），以及全局可路由的IPv4和IPv6地址（如 192.168.179.163 和其他非 fe80:: 开头的IPv6地址）。
     * <p>
     * 在大多数情况下，你可能对全局可路由的IP地址感兴趣，这些地址是在公共互联网上可以被访问的。对于局域网内的通信，192.168.x.x 类型的地址通常是有效的。而对于IPv6，任何非链路本地地址（即不是以 fe80:: 开始的地址）都可以认为是全局可路由的。
     * <p>
     * 要从这些地址中筛选出“真实的”IP地址（即那些可以在互联网上被识别的地址），你可以使用以下逻辑：
     * <p>
     * 1. 排除本地回环地址：127.0.0.1 和 ::1 是仅限于本地主机使用的特殊地址。
     * 2. 排除链路本地地址：所有以 fe80:: 开头的IPv6地址只在本地链路范围内有效。
     * 3. 保留所有其他地址：除了上述两种类型之外的所有地址，通常都是可以在网络上进行通信的有效地址。
     *
     * @return
     */
    public static String getHostIP() {
        List<String> globalIpAddresses = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        globalIpAddresses.add(inetAddress.getHostAddress());
                    }
                }
            }

            return String.join(",", globalIpAddresses);

        } catch (SocketException e) {
            e.printStackTrace();
            return "127.0.0.1";
        }
    }


    /**
     * 获取所有IPv4地址，包括本地回环地址和链路本地地址。
     *
     * @return
     */
    public static String getHostIpAll() {
        List<String> globalIpAddresses = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
//                    System.out.println("IP Address: " + inetAddress.getHostAddress());
                    globalIpAddresses.add(inetAddress.getHostAddress());
                }
            }
            return String.join(",", globalIpAddresses);

        } catch (SocketException e) {
            e.printStackTrace();
            return "127.0.0.1";
        }
    }

    /**
     * 获取访问者IP
     *
     * @param request
     * @return java.lang.String
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-17 13:45
     * @version 1.0.0
     **/
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
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取系统类型
     *
     * @return
     */
    public static String getSystemType() {
        String osName = System.getProperty("os.name");
        if (StringUtils.hasLength(osName)) {
            if (osName.toLowerCase().indexOf("linux") != -1) {
                return "linux";
            }
            if (osName.toLowerCase().indexOf("windows") != -1) {
                return "windows";
            }
            if (osName.toLowerCase().indexOf("mac") != -1) {
                return "mac";
            }
            return "others";
        }
        return "linux";
    }


}
