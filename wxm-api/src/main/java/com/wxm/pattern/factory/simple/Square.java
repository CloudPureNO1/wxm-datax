package com.wxm.pattern.factory.simple;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:33:46
 */

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}