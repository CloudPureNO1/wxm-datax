package com.wxm.security.validatecode;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 14:24:31
 */
public interface ValidateCodeService {
    /**
     * 获取验证码类型
     * @return
     */
    String type();

    /**
     * 构建验证码
     * @param request
     * @return
     */
    ValidateCode build(HttpServletRequest request);

    /**
     * 校验验证码
     * @param validateCode
     * @param request
     */
    void check(ValidateCode validateCode, HttpServletRequest request);

    /**
     * 记录验证码错误次数
     * @param request
     */
    static void errorCountAdd(HttpServletRequest request) {
        Integer errorCount = (Integer)request.getSession().getAttribute("validateCodeErrorCount");
        if (errorCount == null) {
            errorCount = 1;
        } else {
            errorCount = errorCount + 1;
            if (errorCount > 100) {
                errorCount = 100;
            }
        }

        request.getSession().setAttribute("validateCodeErrorCount", errorCount);
    }
}
