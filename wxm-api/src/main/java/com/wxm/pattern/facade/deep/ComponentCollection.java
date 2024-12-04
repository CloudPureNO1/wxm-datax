package com.wxm.pattern.facade.deep;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:42:37
 */

// 集合实现
class ComponentCollection implements ICollection<Component> {
    private List<Component> components = new ArrayList<>();

    public void operation() {
        for (Component component : components) {
            component.operation1();
            component.operation2();
        }
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    // 其他方法
}