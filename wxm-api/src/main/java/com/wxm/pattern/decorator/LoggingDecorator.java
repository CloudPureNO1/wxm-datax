package com.wxm.pattern.decorator;

/**
 * 添加日志记录的装饰者
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 11:43:14
 */
public class LoggingDecorator extends ApiDecorator {
    public LoggingDecorator(BaseApiService apiService) {
        super(apiService);
    }

    @Override
    public String getMessage(String name) {
        // 在调用实际方法前记录日志
        System.out.println("Logging: Getting message for " + name);
        String message = super.getMessage(name); // 调用ApiDecorator中的方法，它会委托给apiService
        // 在调用实际方法后记录日志
        System.out.println("Logging: Message retrieved for " + name);
        return message;
    }
}