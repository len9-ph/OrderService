package com.lgadetsky.orderservice.cache;

/**
 * Inteface for custom cache
 * @author Leonid Gadetsky
 * @param <K> - param that would be key in map
 * @param <V> - param that would be value in map
 */
public interface Cache<K, V> {
	void put(K key, V value);
	V get(K key);
	boolean isEmpty();
	void clear();
}
