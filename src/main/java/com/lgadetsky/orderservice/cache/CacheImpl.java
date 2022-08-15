package com.lgadetsky.orderservice.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lgadetsky.orderservice.model.Session;

public class CacheImpl implements Cache<String, Session> {
	private Map<String, Session> map;
	
	public CacheImpl(List<Session> list) {
		map = new HashMap<>();
		for(Session s : list) {
			map.put(s.getSessionId(), s);
		}
	}

	@Override
	public void put(String key, Session value) {
		map.put(key, value);
	}
	
	@Override
	public Session get(String key) {
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
