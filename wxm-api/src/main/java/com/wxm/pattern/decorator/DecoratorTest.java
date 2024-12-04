package com.wxm.pattern.decorator;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 11:58:27
 */
public class DecoratorTest {

        public static void main(String[] args) {
            // 创建基本的服务实例
            BaseApiService messageService = new MessageService();

            // 使用装饰者添加日志功能  （传入的时上面实例 messageService）
            BaseApiService loggingDecorator = new LoggingDecorator(messageService);

            // 继续装饰，这次添加安全检查功能   （传入的时上面实例 loggingDecorator）
            BaseApiService securityDecorator = new SecurityDecorator(loggingDecorator);

            // 最终的服务将包含所有的装饰功能
            String message = securityDecorator.getMessage("John Doe");
            System.out.println(message);
        }

}
