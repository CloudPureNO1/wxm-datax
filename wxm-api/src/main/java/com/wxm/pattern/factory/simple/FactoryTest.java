package com.wxm.pattern.factory.simple;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:34:30
 */
public class FactoryTest {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("CIRCLE");
        circle.draw();

        Shape square = ShapeFactory.getShape("SQUARE");
        square.draw();
    }
}
