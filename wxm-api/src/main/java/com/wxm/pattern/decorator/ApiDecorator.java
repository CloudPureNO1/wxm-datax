package com.wxm.pattern.decorator;

/**
 * 装饰者基类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 11:42:23
 */
public abstract class ApiDecorator implements BaseApiService {
    protected BaseApiService apiService;

    public ApiDecorator(BaseApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public String getMessage(String name) {
        // 默认行为：直接调用被装饰的apiService
        return apiService.getMessage(name);
    }
}