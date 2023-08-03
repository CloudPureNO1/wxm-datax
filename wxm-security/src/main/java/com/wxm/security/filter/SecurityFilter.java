package com.wxm.security.filter;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxm.base.constrant.CharSetConstants;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import com.wxm.base.exception.DecodeException;
import com.wxm.base.exception.EncodeException;
import com.wxm.base.exception.SecurityException;
import com.wxm.security.wrapper.MyHttpServletRequestWrapper;
import com.wxm.util.my.code.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/8/9 13:39
 * @since 1.0.0
 */
@Order(-97)
@Slf4j
public class SecurityFilter implements Filter {
    /**
     * url 后参数分割符号？
     */
    private static final String URL_PARAMS_SEPARATOR = "\\?";
    /**
     * 不友好api方法的分割符号
     */
    private static final String UN_FRIENDLY_API_SEPARATOR = ",";
    /**
     * 忽略url方法的分割符号
     */
    private static final String IGNORE_URL_SEPARATOR = ",";
    /**
     * 忽略CHARACTER方法的分割符号
     */
    private static final String IGNORE_CHARACTER_SEPARATOR = ",";
    /**
     * base64编码值的api 分隔符
     */
    private static final String BASE64_ENCODE_VALUE_SEPARATOR = ",";
    /**
     * 非法字符提示时，非法字符的分割符号
     */
    private static final String ERROR_MSG_SEPARATOR = ",";
    /**
     * 需要校验的数据格式类型的分割符号
     */
    private static final String MEDIA_TYPE_SEPARATOR = ",";
    /**
     * 配置非法的字符的分割符号
     */
    private static final String ILLEGAL_CHARACTER_SEPARATOR = "\\|";
    /**
     * url 路径的分隔符
     */
    private static final String URL_SEPARATOR = "/";

    private String IllegalCharacter;
    private String ignoreUrl;
    private String needVerifyMediaTypes;
    private String unfriendlyApi;
    private String base64EncodeApi;
    private String ignoreConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.IllegalCharacter = filterConfig.getInitParameter("IllegalCharacter");
        this.ignoreUrl = filterConfig.getInitParameter("ignoreUrl");
        this.needVerifyMediaTypes = filterConfig.getInitParameter("needVerifyMediaTypes");
        this.unfriendlyApi = filterConfig.getInitParameter("unfriendlyApi");
        this.base64EncodeApi = filterConfig.getInitParameter("base64EncodeApi");
        this.ignoreConfig=filterConfig.getInitParameter("ignoreConfig");
    }


    private List<String> getIgnoreUrlCharacter(String servletPath){
        if (StringUtils.hasLength(this.ignoreConfig) && StringUtils.hasLength(servletPath)) {
            String apiUrl = servletPath.split(URL_PARAMS_SEPARATOR)[0];
            String[] apiUrlAry = apiUrl.split(URL_SEPARATOR);
            JSONArray jsonArray=JSON.parseArray(ignoreConfig);
            for(int m=0;m<jsonArray.size();m++){
                JSONObject jsonObject=jsonArray.getJSONObject(m);
                String ignoreUrl=jsonObject.getString("url");
                if(StringUtils.hasLength(ignoreUrl)){
                    String [] ignoreUrls=ignoreUrl.split(IGNORE_URL_SEPARATOR);

             /*       if(Arrays.stream(apiUrlAry).anyMatch(url-> Arrays.stream(ignoreUrls).anyMatch(item->item.equals(url)))){
                        String characters=jsonObject.getString("characters");
                        return Arrays.asList(characters.split(IGNORE_CHARACTER_SEPARATOR));
                    }*/

                    for (String url : ignoreUrls) {
                        boolean bMatch = false;
                        String[] urlAry = url.split(URL_SEPARATOR);
                        if (apiUrlAry.length != urlAry.length) {
                            bMatch = false;
                            break;
                        }

                        for(int i=0;i<apiUrlAry.length;i++){
                            if(apiUrlAry[i].equals(urlAry[i])){
                                bMatch=true;
                                continue;
                            }
                            if(urlAry[i].startsWith("{")&&urlAry[i].endsWith("}")){
                                bMatch=true;
                                continue;
                            }
                            bMatch=false;
                            break;
                        }
                        if(bMatch){
                            String characters=jsonObject.getString("characters");
                            return Arrays.asList(characters.split(IGNORE_CHARACTER_SEPARATOR));
                        }
                    }
                }

            }

        }
        return null;
    }

    /**
     * 请求的接口入参的值是否为base64编码格式
     * @param servletPath
     * @param base64EncodeApi
     * @return
     */
    private boolean isBase64EncodeApi(String servletPath,String base64EncodeApi){
        boolean bContain=false;
        if (StringUtils.hasLength(base64EncodeApi) && StringUtils.hasLength(servletPath)) {
            String apiUrl = servletPath.split(URL_PARAMS_SEPARATOR)[0];

            String[] apiUrlAry = apiUrl.split(URL_SEPARATOR);
            String[] base64EncodeApis = base64EncodeApi.split(BASE64_ENCODE_VALUE_SEPARATOR);
            for (String url : base64EncodeApis) {
                boolean bBase64 = false;
                String[] urlAry = url.split(URL_SEPARATOR);
                if (apiUrlAry.length != urlAry.length) {
                    bBase64 = false;
                    break;
                }

                for(int i=0;i<apiUrlAry.length;i++){
                    if(apiUrlAry[i].equals(urlAry[i])){
                        bBase64=true;
                        continue;
                    }
                    if(urlAry[i].startsWith("{")&&urlAry[i].endsWith("}")){
                        bBase64=true;
                        continue;
                    }
                    bBase64=false;
                    break;
                }
                if(bBase64){
                    bContain=true;
                    break;
                }
            }

        }

        return bContain;
    }


    public boolean isUnfriendly(String servletPath,String unfriendlyApi){
        boolean bContain=false;
        if (StringUtils.hasLength(unfriendlyApi) && StringUtils.hasLength(servletPath)) {
            String apiUrl = servletPath.split(URL_PARAMS_SEPARATOR)[0];

            String[] apiUrlAry = apiUrl.split(URL_SEPARATOR);
            String[] unfriendlyApis = unfriendlyApi.split(UN_FRIENDLY_API_SEPARATOR);
            for (String url : unfriendlyApis) {
                boolean bMatch = false;
                String[] urlAry = url.split(URL_SEPARATOR);
                if (apiUrlAry.length != urlAry.length) {
                    bMatch = false;
                    break;
                }

                for(int i=0;i<apiUrlAry.length;i++){
                    if(apiUrlAry[i].equals(urlAry[i])){
                        bMatch=true;
                        continue;
                    }
                    if(urlAry[i].startsWith("{")&&urlAry[i].endsWith("}")){
                        bMatch=true;
                        continue;
                    }
                    bMatch=false;
                    break;
                }
                if(bMatch){
                    bContain=true;
                    break;
                }
            }

        }
        return bContain;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            response.setCharacterEncoding(CharSetConstants.UTF8);
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");

            security(servletRequest, servletResponse, chain);
        } catch (DecodeException | SecurityException e) {
            DataRtn dataRtn = new DataRtn().code(e.getCode()).message(e.getMessage()).detailMsg(e.getDetailMsg());
            response.getWriter().write(JSON.toJSONString(dataRtn));
        }
    }

    private void security(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException, DecodeException, SecurityException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        StringBuilder builder = new StringBuilder();

        String contentType = request.getContentType();
        // 包含 server.servlet.context-path=/wxm
        String requestURI = request.getRequestURI();
        // 不含 server.servlet.context-path=/wxm
        String servletPath = request.getServletPath();


        boolean isBase64Api=isBase64EncodeApi(servletPath,this.base64EncodeApi);
        boolean isUnfriendly=isUnfriendly(servletPath,this.unfriendlyApi);
        if (!isUnfriendly&&Arrays.stream(this.needVerifyMediaTypes.split(MEDIA_TYPE_SEPARATOR)).anyMatch(mediaType -> mediaType.equals(contentType))) {
            MyHttpServletRequestWrapper myHttpServletRequestWrapper = new MyHttpServletRequestWrapper(request);
            Enumeration<String> paramNames = myHttpServletRequestWrapper.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String str = paramNames.nextElement();
                builder.append(str);

                String value = myHttpServletRequestWrapper.getParameter(str);
                if(StringUtils.hasLength(value)){
                    builder.append(isBase64Api? Base64Util.decode(value, CharSetConstants.UTF8):value);
                }
            }

            String body = myHttpServletRequestWrapper.getBody();
            if(StringUtils.hasLength(body)&&isBase64Api){
                body= Base64Util.decode(body,CharSetConstants.UTF8);
            }

            builder.append(body);
            if (!isInvalid(builder.toString(),servletPath)) {
                chain.doFilter(myHttpServletRequestWrapper, servletResponse);
            }
        } else {
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String str = (String) paramNames.nextElement();
                builder.append(str);

                String value = request.getParameter(str);
                if(StringUtils.hasLength(value)){
                    builder.append(isBase64Api? Base64Util.decode(value,CharSetConstants.UTF8):value);
                }
            }
            if (!isInvalid(builder.toString(),servletPath)) {
                chain.doFilter(servletRequest, servletResponse);
            }
        }
    }


    private boolean isInvalid(String str,String servletPath) throws SecurityException {
        List<String>list=getIgnoreUrlCharacter(servletPath);
        if(!CollectionUtils.isEmpty(list)){
            if(list.stream().anyMatch(character->character.equals("allIgnore"))){
                return false;
            }
            for(String character:list){
                str=str.toLowerCase().replaceAll(character,"");
            }
        }

        String dataStr=str;
        String rs = Arrays.stream(this.IllegalCharacter.split(ILLEGAL_CHARACTER_SEPARATOR)).filter(item -> dataStr.toLowerCase().contains(item.toLowerCase())).collect(Collectors.joining(ERROR_MSG_SEPARATOR));
        if (StringUtils.hasLength(rs)) {
            log.error("【{}】{}:{}", SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getCode(), SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getMsg(), rs);
            throw new SecurityException(SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getCode(),SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getMsg(),SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getMsg()+":"+rs);
        }
        return false;
    }

}
