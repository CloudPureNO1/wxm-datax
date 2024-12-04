package com.wxm.pattern.facade.deep;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:43:13
 */

// 外观
class Facade {
    private ICollection<Component> collection;

    public Facade(ICollection<Component> collection) {
        this.collection = collection;
    }

    public void operation() {
        System.out.println("Facade: operation()");
        collection.operation();
    }
}