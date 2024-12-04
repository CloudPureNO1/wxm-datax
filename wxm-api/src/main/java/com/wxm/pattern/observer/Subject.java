package com.wxm.pattern.observer;



/**
 *
 * 观察者模式在Java中可以通过java.util.Observable类和java.util.Observer接口来实现，但现代Java推荐使用更灵活的事件处理机制，例如监听器和事件监听接口。下面是一个使用接口方式实现观察者模式的简化示例：
 *
 * 首先，我们定义一个Subject接口，它包含添加观察者、删除观察者和通知观察者的功能：
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:55:01
 */

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
