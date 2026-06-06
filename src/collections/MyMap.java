package collections;

public interface MyMap<K, V> {

    int size();

    boolean isEmpty();

    void printAll();

    void put(K key, V value);

    V get(K key);

    V remove(K key);
}
