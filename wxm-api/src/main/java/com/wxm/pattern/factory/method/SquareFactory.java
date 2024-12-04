package com.wxm.pattern.factory.method;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:36:32
 */

class SquareFactory extends AbstractFactory {
    @Override
    Shape getShape() {
        return new Square();
    }
}
