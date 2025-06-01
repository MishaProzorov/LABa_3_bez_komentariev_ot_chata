package com.example.SunriseSunset.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cache {

    private final Map<String, Object> cache;

    public Cache() {
        this.cache = new HashMap<>();
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }
}