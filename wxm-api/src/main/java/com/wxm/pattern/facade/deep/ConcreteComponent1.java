package com.wxm.pattern.facade.deep;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:41:45
 */
// 具体组件
class ConcreteComponent1 implements Component {
    public void operation1() {
        System.out.println("Component1: operation1()");
    }

    public void operation2() {
        System.out.println("Component1: operation2()");
    }
}