package ru.otus.cache.cachehw;


import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    private final WeakHashMap<K, V> cacheHashMap = new WeakHashMap<>();
    private final List<HwListener<K, V>> listenerList = new ArrayList<>();

    private static final String ACTION_PUT = "PUT";
    private static final String ACTION_REMOVE = "REMOVE";

    @Override
    public void put(K key, V value) {
        cacheHashMap.put(key, value);
        sendNotify(key, value, ACTION_PUT);
    }

    @Override
    public void remove(K key) {
        cacheHashMap.remove(key);
        sendNotify(key, null, ACTION_REMOVE);
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

    private void sendNotify(K key, V value, String action) {
        for (var listener: listenerList) {
            try {
                listener.notify(key, value, action);
            } catch (Exception ignored) { }
        }
    }

}
