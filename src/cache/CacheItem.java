package cache;


import java.lang.ref.SoftReference;

public class CacheItem<K, V> {

    public K key;
    public SoftReference<V> valueRef;
    public long createTime;
    public long aliveTime;

    public CacheItem(K key, V value, long createTime, long aliveTime) {
        this.key = key;
        this.valueRef = new SoftReference<>(value);
        this.createTime = createTime;
        this.aliveTime = aliveTime;
    }
}