/**
 * 
 */
package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Session;
import com.lgadetsky.orderservice.repository.mapper.SessionMapper;

/**
 * @author Leonid Gadetsky
 *
 */
@org.springframework.stereotype.Service
public class SessionService implements Service<Session, String> {
	
	private final SessionMapper sessionMapper;

	public SessionService(SessionMapper sessionMapper) {
		this.sessionMapper = sessionMapper;
	}

	@Override
	public Session create(Session session) {
		sessionMapper.insert(session);
		return session;
	}

	@Override
	public Session findById(String id) {
		return sessionMapper.findBySessionId(id);
	}

	@Override
	public Session update(Session session) {
		sessionMapper.update(session);
		return session;
	}

	@Override
	public void deleteById(String id) {
		sessionMapper.deleteBySessionId(id);
	}

}
