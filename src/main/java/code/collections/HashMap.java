package code.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * ToDo
 *
 * @param <K> Key
 * @param <V> Value
 */
public class HashMap<K, V> {

    public static final long serialversionUid = 123456789012345678L;

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    private transient Node<K, V>[] table;

    private transient int size;

    public HashMap() {
        this (DEFAULT_INITIAL_CAPACITY);
    }

    public HashMap (int capacity) {
        table = new Node[capacity];
    }

    private static final int hash (Object key) {
        int h;
        return (null == key) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        int bucket = hash(key) % table.length;
        Node<K, V> node = new Node(hash(key), key, value, null);
        if (table[bucket] == null) {
            table[bucket] = node;
        } else {
            Node<K, V> n = table[bucket];
            while (n != null) {
                if (n.key.equals(node.key)) return n.value = node.value;
                n = n.next;
            }

        }
        return null;
    }


    public V remove(Object key) {
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {

    }

    public void clear() {

    }


    public Set<K> keySet() {
        return null;
    }


    public Collection<V> values() {
        return null;
    }


    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    static class Node<K, V> implements Map.Entry<K, V> {

        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node (int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        public final int hashCode () {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

}
