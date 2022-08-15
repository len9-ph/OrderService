/**
 * 
 */
package com.lgadetsky.orderservice.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lgadetsky.orderservice.model.Session;

/**
 * @author Leonid Gadetsky
 *
 */
@Mapper
public interface SessionMapper {
	void insert(Session session);
	Session findBySessionId(String sessionId);
	List<Session> findAll();
	void update(Session session);
	void deleteBySessionId(String sessionId);
}
