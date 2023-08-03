package com.wxm.opencv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = { "com.wxm" })
@SpringBootApplication
public class WxmOpencvApplication {

    public static void main(String[] args) {
        /**
         * java.awt.HeadlessException  解决
         *
         *
         * 原因
         * 此错误异常是在写文档打印接口时遇到的，从异常信息可以看到HeadlessException是项目开启了Headless模式，在这种模式下系统就会缺少其他设备的支持，比如：显示设备、键盘、鼠标等外设。Headless模式在服务端是很常用的，因为大多数服务器就是运行在没有前述设备支持的情况下。
         * 注意：若是要部署到不具有响应外设支持的服务器上，则需要使用计算能力模拟出能力，或者使用远程支持。
         *
         * 解决方法
         * 在程序的入口类里修改成以下代码,其中Application即为程序入口类的名字 ，web(false)改为web(WebApplicationType.SERVLET)
         *
         * SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
         * builder.headless(false).web(WebApplicationType.SERVLET).run(args);
         * 1
         * 2
         * WebApplicationType，枚举类，为web应用程序的类型，查看源码，分为三类：
         * NONE：该应用程序不应作为Web应用程序运行，也不应启动嵌入式Web服务器。
         * SERVLET：默认springboot启动上下文类型就是servlet。
         * REACTIVE：该应用程序应作为反应式Web应用程序运行，并应启动嵌入式反应式Web服务器 。
         */
        SpringApplicationBuilder builder = new SpringApplicationBuilder(WxmOpencvApplication.class);
        builder.headless(false).web(WebApplicationType.SERVLET).run(args);
//        SpringApplication.run(WxmOpencvApplication.class, args);
    }

}
