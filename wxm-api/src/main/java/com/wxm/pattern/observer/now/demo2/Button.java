package com.wxm.pattern.observer.now.demo2;
import java.util.ArrayList;
import java.util.List;
/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:02:54
 */
public class Button {
    private final List<ActionListener> listeners = new ArrayList<>();

    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    public void removeActionListener(ActionListener listener) {
        listeners.remove(listener);
    }

    // 模拟按钮点击事件
    public void click(String action) {
        ActionEvent event = () -> action; // 实现ActionEvent接口
        notifyListeners(event);
    }

    private void notifyListeners(ActionEvent event) {
        for (ActionListener listener : listeners) {
            listener.onAction(event);
        }
    }
}
