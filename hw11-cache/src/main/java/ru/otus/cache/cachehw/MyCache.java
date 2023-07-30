package ru.otus.cache.cachehw;


import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    private final WeakHashMap<K, V> cacheHashMap = new WeakHashMap<>();
    private final List<HwListener<K, V>> listenerList = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        cacheHashMap.put(key, value);
    }

    @Override
    public void remove(K key) {
        cacheHashMap.remove(key);
    }

    @Override
    public V get(K key) {
        return cacheHashMap.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listenerList.remove(listener);
    }
}
