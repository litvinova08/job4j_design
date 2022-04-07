package ru.job4j.map;

import java.util.*;

/**
 * this is a realisation of HashMap
 * @param <K> first parameter type
 * @param <V> second parameter type
 * @author Margarita L
 * @version 1.0
 */
public class SimpleMap<K, V> implements SMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * this method checks capacity and expands a map if needed
     */
    private void expand() {
        int size = count / table.length;
        if (size >= LOAD_FACTOR) {
            capacity = capacity * 2;
            MapEntry<K, V>[] tableV2 = new MapEntry[capacity];
        }
    }

    public int getCount() {
        return this.count;
    }

    /**
     * the method adds new node key-value into a hashmap
     * the method expands automatically if a hashmap is full
     * @param key
     * @param value
     * @return true if a new node added successfully
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        expand();
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            table[index] = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private  int expectedModCount = modCount;
            private int position = 0;
            @Override
            public boolean hasNext() {
                return position < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[position] == null) {
                    position++;
                }
                return table[position++].key;
            }
        };
    }


private static class MapEntry<K, V> {

    K key;
    V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
}
