package com.code.test.todolist.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.code.test.todolist.service.impl.CustomUserDetailsService;
import com.code.test.todolist.session.SessionFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private SessionFilter sessionFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http = http.cors().and().csrf().disable();

		http = http.exceptionHandling().authenticationEntryPoint(
				(request, response, ex) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()))
				.and();

//      http.authorizeRequests()
//      .antMatchers("/todo").hasRole(UserRole.USER.toString())
//      .antMatchers("/login").permitAll()
//      .and().formLogin();

		/*
		 * Adding H2-console to get access of h2 without auth
		*/
		http.authorizeRequests().antMatchers("/h2/**").permitAll();
		http.headers().frameOptions().disable();
		
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();

		http.addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
