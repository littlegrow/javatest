package online;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 实现一个缓存容器，支持容量指定和超出容量按照热度淘汰
 * 同时尽可能保证多线程环境的读写性能
 * 不能使用外部三方库
 */

interface ICache<K, V> {
    V get(K key);

    boolean put(K key, V value);

    boolean delete(K key);

    boolean clear();
}

class CacheItem<K, V> {
    private long hot;
    private K key;
    private V value;

    CacheItem(K key, V value) {
        this.key = key;
        this.value = value;
        this.hot = 1;
    }

    long getHot() {
        return hot;
    }

    void increaseHot() {
        this.hot++;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("key: %s, value: %s, hot: %d", key.toString(), value.toString(), hot);
    }
}

class Cache<K, V> implements ICache<K, V> {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int maxSize;
    private Map<K, CacheItem<K, V>> data;

    Cache(int maxSize) {
        this.data = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    public V get(K key) {
        try {
            lock.readLock().lock();
            CacheItem<K, V> item = data.get(key);
            if (item != null) {
                item.increaseHot();
                System.out.println("get cache: " + item);
                return item.getValue();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return null;
    }

    @Override
    public boolean put(K key, V value) {
        try {
            lock.writeLock().lock();
            if (data.size() >= maxSize) {
                removeMinHotItem();
            }
            CacheItem<K, V> item = data.get(key);
            if (item != null) {
                item.increaseHot();
            } else {
                item = new CacheItem<>(key, value);
                data.put(key, item);
            }
            System.out.println("save cache: " + item);
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.writeLock().unlock();
        }
        return true;
    }


    @Override
    public boolean delete(K key) {
        try {
            lock.writeLock().lock();
            CacheItem<K, V> item = data.remove(key);
            System.out.println("delete cache: " + item);
            return item != null;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
        return false;
    }

    @Override
    public boolean clear() {
        try {
            lock.writeLock().lock();
            data.clear();
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void removeMinHotItem() {
        Collection<CacheItem<K, V>> cacheItems = data.values();
        Iterator<CacheItem<K, V>> iterator = cacheItems.iterator();
        if (iterator.hasNext()) {
            CacheItem<K, V> minHotItem = iterator.next();
            while (iterator.hasNext()) {
                CacheItem<K, V> tmp = iterator.next();
                if (tmp.getHot() < minHotItem.getHot()) {
                    minHotItem = tmp;
                }
            }
            System.out.println("delete cache: " + minHotItem);
            data.remove(minHotItem.getKey());
        }
    }
}


public class CacheManager {
    private static CacheManager instance = new CacheManager();
    private ICache<String, Object> cache = new Cache<>(50);

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        return instance;
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public boolean put(String key, Object value) {
        return cache.put(key, value);
    }

    public boolean delete(String key) {
        return cache.delete(key);
    }

    public boolean clear() {
        return cache.clear();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                CacheManager.getInstance().get("key" + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                CacheManager.getInstance().put("key" + i, i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 5; i < 55; i++) {
                CacheManager.getInstance().get("key" + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 10; i < 60; i++) {
                CacheManager.getInstance().get("key" + i);
            }
        }).start();
    }
}
