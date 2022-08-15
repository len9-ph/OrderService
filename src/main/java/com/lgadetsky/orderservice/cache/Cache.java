package com.lgadetsky.orderservice.cache;

public interface Cache<K, V> {
	boolean load();
	void put(K key, V value);
	V get(K key);
	boolean isEmpty();
	void clear();
}
