package com.wxm.pattern.observer.now.demo1;
import java.util.ArrayList;
import java.util.List;
/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:10:12
 */
public class EventPublisher {
    private List<EventListener> listeners = new ArrayList<>();

    public void register(EventListener listener) {
        listeners.add(listener);
    }

    public void unregister(EventListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(Event event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    public void fireEvent(String message) {
        Event event = new Event() {
            @Override
            public String getMessage() {
                return message;
            }
        };
        notifyListeners(event);
    }
}
