package com.wxm.pattern.observer;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:57:10
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}