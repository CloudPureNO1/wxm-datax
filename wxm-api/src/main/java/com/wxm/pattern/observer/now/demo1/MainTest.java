package com.wxm.pattern.observer.now.demo1;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:10:43
 */
public class MainTest {
    public static void main(String[] args) {
        EventPublisher publisher = new EventPublisher();

        EventListener listener1 = event -> System.out.println("Listener 1 received event: " + event.getMessage());
        EventListener listener2 = event -> System.out.println("Listener 2 received event: " + event.getMessage());

        publisher.register(listener1);
        publisher.register(listener2);

        publisher.fireEvent("Hello, world!");

        publisher.unregister(listener2);
        // 移除 监听2 之后，信息在变化，比如输入Goodbye, world!时，那么只有监听1收到信息
        publisher.fireEvent("Goodbye, world!");
    }
}
