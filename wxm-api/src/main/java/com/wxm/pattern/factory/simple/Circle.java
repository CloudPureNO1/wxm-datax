package com.wxm.pattern.factory.simple;

/**
 * 具体产品类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:33:22
 */

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}
