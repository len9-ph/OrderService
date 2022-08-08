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

import com.lgadetsky.orderservice.model.Session;
import com.lgadetsky.orderservice.service.SessionService;

/**
 * @author Leonid Gadetsky
 *
 */
@WebFilter(urlPatterns = "/servlet")
public class OrderFilter implements Filter{
	
	private final SessionService sessionService;
	
	public OrderFilter(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String sessionId = request.getParameter("session-id");
		if (sessionId != null) {
			Session session = sessionService.findById(sessionId);
			if (session != null) 
				if (isSessionValid(session))
					chain.doFilter(request, response);
				else
					response.getWriter().println("Session expired");
			else 
				response.getWriter().println("Unknown session");
		}
		else
			response.getWriter().println("Invalid session");
		
	}
	
	private boolean isSessionValid(Session session) {
		return (new Date().getTime() - session.getStartTime().getTime()) < (session.getTimeoutMinutes() * 60000);
	}
	
	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}

}
