package org.Smok3ALot.SM_Core.Cache;

import org.Smok3ALot.SM_Core.SM_Core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {

    private SM_Core core;
    // Map: PlayerUUID -> Key -> Value
    private final Map<UUID, Map<String, Object>> playerCache = new ConcurrentHashMap<>();

    public CacheManager(SM_Core core) {
        this.core = core;
    }

    // Daten setzen
    public void put(UUID playerId, String key, Object value) {
        playerCache.computeIfAbsent(playerId, k -> new ConcurrentHashMap<>()).put(key, value);
    }

    // Daten abrufen
    public Object get(UUID playerId, String key) {
        Map<String, Object> cache = playerCache.get(playerId);
        return (cache != null) ? cache.get(key) : null;
    }

    // Daten abrufen mit Default
    public Object getOrDefault(UUID playerId, String key, Object defaultValue) {
        Map<String, Object> cache = playerCache.get(playerId);
        return (cache != null) ? cache.getOrDefault(key, defaultValue) : defaultValue;
    }

    // Daten entfernen
    public void remove(UUID playerId, String key) {
        Map<String, Object> cache = playerCache.get(playerId);
        if (cache != null) {
            cache.remove(key);
        }
    }

    // Kompletten Cache für Spieler leeren
    public void clear(UUID playerId) {
        playerCache.remove(playerId);
    }

    // Kompletten Cache leeren
    public void clearAll() {
        playerCache.clear();
    }

    // Prüfen ob Wert existiert
    public boolean contains(UUID playerId, String key) {
        Map<String, Object> cache = playerCache.get(playerId);
        return cache != null && cache.containsKey(key);
    }
}
