package com.code.test.todolist.session;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.code.test.todolist.exception.CustomApiException;

@Configuration
public class SessionManager {
	
	private final static Map<String, String> SESSIONS = new HashMap<String, String>();
	
	private String generateSessionId() {
		return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
	}
	
	public String getUsernameBySession(String sessionId) {
		return SESSIONS.get(sessionId);
	}
	
	public void clearSessionId(String sessionId) {
		SESSIONS.remove(sessionId);
	}
	
	public String registerSession(String username) {
		if(!StringUtils.hasText(username)) {
			throw new CustomApiException("Username cannot be null or empty!");
		}
		String sessionId = generateSessionId();
		SESSIONS.putIfAbsent(sessionId, username);
		return sessionId;
	}

}
   