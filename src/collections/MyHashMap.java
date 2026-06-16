package collections;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    public MyHashMap() {
        buckets = new LinkedList[CAPACITY];
    }

    private static class Entry<K, V> {
        K key;
        V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    private int getIndexBucket(K key, int capacity) {
        if (key != null) {
            int hash = key.hashCode();
            int positiveHash = hash == Integer.MIN_VALUE ? 0 : Math.abs(hash);
            return positiveHash % capacity;
        }
        return 0;
    }

    private int getIndexBucket(K key) {
        return getIndexBucket(key, buckets.length);
    }

    private void changeOfSize() {
        int newCapacity = buckets.length * 2;
        LinkedList<Entry<K, V>>[] newBuckets = new LinkedList[newCapacity];

        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int newIndex = getIndexBucket(entry.key, newCapacity);
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new LinkedList<>();
                    }
                    newBuckets[newIndex].add(entry);
                }
            }
        }
        buckets = newBuckets;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printAll() {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (Entry<K, V> entry : buckets[i]) {
                    System.out.println(entry.key + " = " + entry.value);
                }
            }
        }

        if (isEmpty()) {
            System.out.println("Данные отсутствуют");
        }
    }

    @Override
    public void put(K key, V value) {
        int index = getIndexBucket(key);

        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));
        size++;

        if ((double) size / buckets.length > 0.75) {
            changeOfSize();
        }
    }

    @Override
    public V get(K key) {
        int index = getIndexBucket(key);

        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = getIndexBucket(key);

        if (buckets[index] == null) {
            return null;
        }

        Iterator<Entry<K, V>> iterator = buckets[index].iterator();

        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                iterator.remove();
                size--;
                return oldValue;
            }

        }
        return null;
    }
}
