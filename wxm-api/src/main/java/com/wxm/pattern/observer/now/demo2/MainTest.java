package com.wxm.pattern.observer.now.demo2;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:03:16
 */
public class MainTest {
    public static void main(String[] args) {
        Button button = new Button();

        // 使用匿名内部类创建监听器
        button.addActionListener(new ActionListener() {
            @Override
            public void onAction(ActionEvent event) {
                System.out.println("Button clicked with action: " + event.getAction());
            }
        });

        // 或者使用lambda表达式创建监听器
        button.addActionListener(event -> System.out.println("Another listener triggered by action: " + event.getAction()));

        // 模拟点击事件
        button.click("ClickMe");
    }
}
