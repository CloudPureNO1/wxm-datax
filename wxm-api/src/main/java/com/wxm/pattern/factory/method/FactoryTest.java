package com.wxm.pattern.factory.method;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:36:47
 */
public class FactoryTest {
    public static void main(String[] args) {
        AbstractFactory circleFactory = new CircleFactory();
        Shape circle = circleFactory.getShape();
        circle.draw();

        AbstractFactory squareFactory = new SquareFactory();
        Shape square = squareFactory.getShape();
        square.draw();
    }
}
