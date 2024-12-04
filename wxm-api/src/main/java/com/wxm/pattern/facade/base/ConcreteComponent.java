package com.wxm.pattern.facade.base;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:38:32
 */
// 具体组件
class ConcreteComponent implements Component {
    public void operation1() {
        System.out.println("Component: operation1()");
    }

    public void operation2() {
        System.out.println("Component: operation2()");
    }
}