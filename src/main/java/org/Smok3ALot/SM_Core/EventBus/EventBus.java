package org.Smok3ALot.SM_Core.EventBus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus {

    private final Map<Class<?>, List<EventListener<?>>> listeners = new ConcurrentHashMap<>();

    // Listener registrieren
    public <T> void registerListener(Class<T> eventType, EventListener<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    // Event feuern
    @SuppressWarnings("unchecked")
    public <T> void fireEvent(T event) {
        List<EventListener<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (EventListener<?> l : eventListeners) {
                ((EventListener<T>) l).onEvent(event);
            }
        }
    }
}
