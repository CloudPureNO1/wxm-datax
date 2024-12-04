package com.wxm.pattern.facade.base;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:39:22
 */
// 客户端
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Facade facade = new Facade(component);
        facade.operation();
    }
}