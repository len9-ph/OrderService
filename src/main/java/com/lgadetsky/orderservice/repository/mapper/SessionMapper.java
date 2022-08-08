/**
 * 
 */
package com.lgadetsky.orderservice.repository.mapper;

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
	void update(Session session);
	void deleteBySessionId(String sessionId);
}
