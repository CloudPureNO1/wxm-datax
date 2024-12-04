package com.wxm.pattern.factory.simple;

/**
 * 简单工厂类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:34:03
 */
class ShapeFactory {
    public static Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
