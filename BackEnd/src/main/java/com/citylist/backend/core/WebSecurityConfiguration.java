package com.citylist.backend.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 ** @BMN 2021
 **
 **/

@EnableWebMvc
@Configuration
@Import({ SecurityConfiguration.class })
public class WebSecurityConfiguration implements WebMvcConfigurer {
	@Value("${corsValue}")
	private String corsValue;

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins(corsValue);

	}
}