package org.Smok3ALot.SM_Core.EventBus;

public interface EventListener<T> {
    void onEvent(T event);
}

