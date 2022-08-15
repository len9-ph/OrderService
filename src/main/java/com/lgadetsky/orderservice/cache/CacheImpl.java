package com.lgadetsky.orderservice.cache;

import java.util.Map;

public class CacheImpl<K, V> implements Cache<K, V> {
	private Map<K, V> map;
	
	
	
	@Override
	public boolean load() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void put(K key, V value) {
		map.put(key, value);
	}
	
	@Override
	public V get(K key) {
		if (map.get(key) != null)
			return map.get(key);
		return null;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public void clear() {
		map.clear();
	}
}
