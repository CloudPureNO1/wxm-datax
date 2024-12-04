package com.wxm.pattern.observer;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 14:58:23
 */
public class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature +
                " F degrees and " + humidity + "% humidity");
    }
}
