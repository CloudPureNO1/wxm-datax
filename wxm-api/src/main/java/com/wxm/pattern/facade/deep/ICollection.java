package com.wxm.pattern.facade.deep;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 10:42:20
 */
// 集合接口
interface ICollection<Component> {
    void operation();

    void addComponent(Component component);
}
