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
		if(sessionService.findById(request.getParameter("session-id")) != null) {
			Session session = sessionService.findById(request.getParameter("session-id"));
			if(isTimeValid(session))
				chain.doFilter(request, response);
			else 
				response.getWriter().print("Session expired");
		}
		else
			response.getWriter().print("Unknown session");
		
	}
	
	private boolean isTimeValid(Session session) {
		return (new Date().getTime() - session.getStartTime().getTime()) > session.getTimeoutMinutes()  ;
	}
	
	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}

}
