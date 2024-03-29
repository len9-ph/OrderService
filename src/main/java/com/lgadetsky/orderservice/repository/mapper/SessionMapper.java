/**
 * 
 */
package com.lgadetsky.orderservice.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lgadetsky.orderservice.model.Session;

/**
 * Mapper responsible for mapping the Session entity
 * @author Leonid Gadetsky
 * @see Session
 */
@Mapper
public interface SessionMapper {
	int insert(Session session);
	Session findBySessionId(String sessionId);
	List<Session> findAll();
	int update(Session session);
	int deleteBySessionId(String sessionId);
}
