/**
 * 
 */
package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.Session;

/**
 * @author Leonid Gadetsky
 *
 */
public interface SessionMapper {
	void insert(Session session);
	Session findBySessionId(String sessionId);
	void update(Session session);
	void deleteBySessionId(String sessionId);
}
