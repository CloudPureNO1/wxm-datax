package com.wxm.test.main;

import com.alibaba.fastjson.JSON;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/8/9 13:39
 * @since 1.0.0
 */
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.IllegalCharacter = filterConfig.getInitParameter("IllegalCharacter");
        this.ignoreUrl = filterConfig.getInitParameter("ignoreUrl");
        this.needVerifyMediaTypes = filterConfig.getInitParameter("needVerifyMediaTypes");
        this.unfriendlyApi = filterConfig.getInitParameter("unfriendlyApi");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");

        StringBuilder builder = new StringBuilder();

        String contentType = request.getContentType();
        // 包含 server.servlet.context-path=/wxm
        String requestURI = request.getRequestURI();
        // 不含 server.servlet.context-path=/wxm
        String servletPath = request.getServletPath();
        boolean bContain=false;
        if (StringUtils.hasLength(this.unfriendlyApi) && StringUtils.hasLength(servletPath)) {
            String apiUrl = servletPath.split(URL_PARAMS_SEPARATOR)[0];

            String[] apiUrlAry = apiUrl.split(URL_SEPARATOR);
            String[] unfriendlyApis = this.unfriendlyApi.split(UN_FRIENDLY_API_SEPARATOR);
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
                    if(apiUrlAry[i].startsWith("{")&&apiUrlAry[i].endsWith("}")){
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

        if (!bContain&&Arrays.stream(this.needVerifyMediaTypes.split(MEDIA_TYPE_SEPARATOR)).anyMatch(mediaType -> mediaType.equals(contentType))) {
            MyHttpServletRequestWrapper myHttpServletRequestWrapper = new MyHttpServletRequestWrapper(request);
            Enumeration<String> paramNames = myHttpServletRequestWrapper.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String str = paramNames.nextElement();
                builder.append(str);
            }
            String body = myHttpServletRequestWrapper.getBody();
            builder.append(body);
            if (!isInvalid(builder.toString(), response)) {
                chain.doFilter(myHttpServletRequestWrapper, servletResponse);
            }
        } else {
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String str = (String) paramNames.nextElement();
                builder.append(str);
            }
            if (!isInvalid(builder.toString(), response)) {
                chain.doFilter(servletRequest, servletResponse);
            }
        }
    }


    private boolean isInvalid(String str, HttpServletResponse response) throws IOException {
        String rs = Arrays.stream(this.IllegalCharacter.split(ILLEGAL_CHARACTER_SEPARATOR)).filter(item -> str.toLowerCase().contains(item.toLowerCase())).collect(Collectors.joining(ERROR_MSG_SEPARATOR));
        if (StringUtils.hasLength(rs)) {
            log.info("【{}】{}:{}", SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getCode(), SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getMsg(), rs);
            DataRtn dataRtn = new DataRtn().failure().message(SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getCode()).detailMsg(SecurityInfoEnum.SECURITY_CHECK_INJECT_XSS.getMsg());
            response.getWriter().write(JSON.toJSONString(dataRtn));
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
