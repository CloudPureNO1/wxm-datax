package com.wxm.pattern.factory.method;

/**
 * 具体工厂类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:36:15
 */

class CircleFactory extends AbstractFactory {
    @Override
    Shape getShape() {
        return new Circle();
    }
}