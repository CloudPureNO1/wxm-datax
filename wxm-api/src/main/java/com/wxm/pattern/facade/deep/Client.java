package com.wxm.pattern.facade.deep;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:43:29
 */

// 客户端
public class Client {
    public static void main(String[] args) {
        ICollection<Component> collection = new ComponentCollection();
        collection.addComponent(new ConcreteComponent1());
        collection.addComponent(new ConcreteComponent2());
        Facade facade = new Facade(collection);
        facade.operation();
    }
}