package com.code.test.todolist.session;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.code.test.todolist.model.User;
import com.code.test.todolist.service.impl.CustomUserDetailsService;

@Configuration
public class SessionFilter extends OncePerRequestFilter {
	
	@Autowired
	private SessionManager sessionManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!StringUtils.hasLength(sessionId)) {
			filterChain.doFilter(request, response);
			return;
		}
		String username = sessionManager.getUsernameBySession(sessionId);
		if(!StringUtils.hasLength(username)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		User user = (User) customUserDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);

	}

}
