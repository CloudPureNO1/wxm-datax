package com.wxm.pattern.decorator;

/**
 * 添加安全检查的装饰者
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 11:56:53
 */
public class SecurityDecorator extends ApiDecorator {
    public SecurityDecorator(BaseApiService apiService) {
        super(apiService);
    }

    @Override
    public String getMessage(String name) {
        // 在调用实际方法前进行安全检查
        if (isRequestSecure(name)) {
            System.out.println(">>>>>>>>>>>校验通过");
            return super.getMessage(name);
        } else {
            return "Access denied.";
        }
    }

    private boolean isRequestSecure(String name) {
        // 检查请求是否安全
        return true; // 示例代码，应替换成实际的安全检查逻辑
    }
}