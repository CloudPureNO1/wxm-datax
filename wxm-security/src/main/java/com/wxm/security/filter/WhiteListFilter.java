package com.wxm.security.filter;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import com.wxm.util.my.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/3/17 13:13
 * @since 1.0.0
 */
@Order(-99)
@Slf4j

public class WhiteListFilter implements Filter {
    private String ips;
    private String ignoreUrl;
    private String contentPath;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>WhiteListFilter init");
         ips=filterConfig.getInitParameter("ips");
         ignoreUrl=filterConfig.getInitParameter("ignoreUrl");
        contentPath=filterConfig.getInitParameter("contentPath");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        
//        try{
//
//
//            log.info("白名单：{}",ips);
//            String ip= IPUtils.getIpAddr(request);
//            log.info("当前的请求ip:{}，请求url:{}",ip,request.getRequestURI());
//            System.out.println("当前的请求ip:{"+ip+"}，请求url:{"+request.getRequestURI()+"}");
//            if(passUrl(request.getRequestURI())){
//                log.info("忽略路径：{}",request.getRequestURI());
//                filterChain.doFilter(request, response);
//            }else if(checkWhiteIps(ip,ips)){
//                filterChain.doFilter(servletRequest,servletResponse);
//            }else{
//                log.info("【{}】{}:{}", SecurityInfoEnum.SECURITY_CHECK_NO_PASS.getCode(),SecurityInfoEnum.SECURITY_CHECK_NO_PASS.getMsg(),ip);
//                DataRtn dataRtn=new DataRtn().failure().message(SecurityInfoEnum.SECURITY_CHECK_NO_PASS.getCode()).detailMsg(SecurityInfoEnum.SECURITY_CHECK_NO_PASS.getMsg());
//                response.getWriter().write(JSON.toJSONString(dataRtn));
//            }
//        }catch (Exception e) {
//            log.error("【{}】{}：{}",SecurityInfoEnum.SECURITY_CHECK_FAILURE.getCode(),SecurityInfoEnum.SECURITY_CHECK_FAILURE.getMsg(),e.getMessage(),e);
//            DataRtn dataRtn=new DataRtn().failure().message(SecurityInfoEnum.SECURITY_CHECK_FAILURE.getCode()).detailMsg(SecurityInfoEnum.SECURITY_CHECK_FAILURE.getMsg());
//            response.getWriter().write(JSON.toJSONString(dataRtn));
//        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        if(ips!=null){
            ips=null;
        }
    }

    public boolean passUrl(String url){
        if(StringUtils.hasLength(ignoreUrl)&& Arrays.stream(ignoreUrl.split(",")).anyMatch(item->url.startsWith(item)||url.startsWith(contentPath+item))){
            return true;
        }
        return false;
    }

    public boolean checkWhiteIps(String ip, String ips){
        if(StringUtils.hasLength(ips)&&Arrays.stream(ips.split(",")).anyMatch(item->item.equals(ip))){
            return true;
        }
        return false;
    }


}
