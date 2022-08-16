package com.lgadetsky.orderservice.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lgadetsky.orderservice.model.Session;
import com.lgadetsky.orderservice.service.SessionService;

@Component
public class CacheImpl implements Cache<String, Session> {
	private Map<String, Session> map;
	
	private static SessionService service;
	
	private CacheImpl(SessionService sessionService) {
		CacheImpl.service = sessionService;
		List<Session> list = service.findAll();
		map = new HashMap<>();
		for(Session s : list) {
			map.put(s.getSessionId(), s);
		}
	}
	
	private static CacheImpl instance;
	
	public static CacheImpl getInstance() {
		if (instance == null)
			try {
				instance = new CacheImpl(service);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return instance;
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
