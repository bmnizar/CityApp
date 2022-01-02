package com.citylist.backend.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(new JWTAuthorizationFilter(authenticationManager()),
//				UsernamePasswordAuthenticationFilter.class);
		http.headers().contentTypeOptions().and().xssProtection().and().cacheControl().and()
				.httpStrictTransportSecurity().and().httpStrictTransportSecurity().and()
				.addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"));
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll();

	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
		return bCryptPasswordEncoder;
	}

}