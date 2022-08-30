/**
 * 
 */
package com.lgadetsky.orderservice.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.lgadetsky.orderservice.cache.CacheImpl;
import com.lgadetsky.orderservice.model.Session;
import com.lgadetsky.orderservice.service.SessionService;

/**
 * Filter class that checked sessions correctness
 * User can get access only if session isn't expire 
 * or user have admin rights -> timeOutInMinutes equals 0
 * 
 * @author Leonid Gadetsky
 * @see SessionService
 */
@WebFilter(urlPatterns = "/servlet")
public class OrderFilter implements Filter{
	private static final String SESSION_ID = "session-id";
	private static final String INVALID_SESSION = "Invalid session";
    private static final String UNKNOWN_SESSION = "Unknown session";
    private static final String SESSION_EXPIRED = "Session expired";
    
	@Autowired
	private SessionService sessionService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String sessionId = request.getParameter(SESSION_ID);
		if (sessionId != null) {
			if (CacheImpl.getInstance().get(sessionId) == null)
				CacheImpl.getInstance().put(sessionService.findById(sessionId).getSessionId(), sessionService.findById(sessionId));
				 
			Session session = CacheImpl.getInstance().get(sessionId);
			if (session != null) 
				if (isSessionValid(session))
					chain.doFilter(request, response);
				else
					response.getWriter().println(SESSION_EXPIRED);
			else 
				response.getWriter().println(UNKNOWN_SESSION);
		}
		else
			response.getWriter().println(INVALID_SESSION);
		
	}
	
	private boolean isSessionValid(Session session) {
		return session.getTimeoutMinutes() == 0 | (new Date().getTime() - session.getStartTime().getTime()) < (session.getTimeoutMinutes() * 60000);
	}
	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}

}
