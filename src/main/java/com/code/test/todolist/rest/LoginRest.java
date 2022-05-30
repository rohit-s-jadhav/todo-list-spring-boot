package com.code.test.todolist.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.code.test.todolist.session.SessionManager;
import com.code.test.todolist.to.LoginTO;
import com.code.test.todolist.to.UserTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // UI port
public class LoginRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginRest.class);

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private SessionManager sessionManager;

	@PostMapping("/login")
	public ResponseEntity<LoginTO> login(@RequestBody UserTO user) {
		LOGGER.info("Login attempted with user : "+ user.getUsername());
		authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		final String sessionId = sessionManager.registerSession(user.getUsername());
		LoginTO resp = new LoginTO();
		resp.setUsername(user.getUsername());
		resp.setSessionId(sessionId);

		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/log-out")
	public ResponseEntity<LoginTO> logout(@RequestHeader("Authorization") String authToken) {
		sessionManager.clearSessionId(authToken);
		return ResponseEntity.ok(new LoginTO());
	}

}