package com.wxm.pattern.factory.method;

/**
 * 具体产品类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:35:27
 */

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}