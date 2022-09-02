/**
 * 
 */
package com.lgadetsky.orderservice.service;

import java.util.List;

import com.lgadetsky.orderservice.model.Session;
import com.lgadetsky.orderservice.repository.mapper.SessionMapper;

/**
 * Service class that implements {@link Service} interface
 * Work with {@link SessionMapper}
 * 
 * @author Leonid Gadetsky
 * @see Service
 * @see SessionMapper
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
	
	public List<Session> findAll(){
		return sessionMapper.findAll();
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
