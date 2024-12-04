package com.wxm.pattern.facade.base;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:39:01
 */
// 外观
class Facade {
    private Component component;

    public Facade(Component component) {
        this.component = component;
    }

    public void operation() {
        System.out.println("Facade: operation()");
        component.operation1();
        component.operation2();
    }
}