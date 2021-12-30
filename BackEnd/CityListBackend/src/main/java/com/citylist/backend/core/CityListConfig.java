package com.citylist.backend.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.web.client.RestTemplate;

/**
 ** @BMN 2021
 **
 **/
@Configuration
public class CityListConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_ALLOW_EDIT";
		roleHierarchy.setHierarchy(hierarchy);
		return roleHierarchy;
	}

//	@Bean
//	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
//		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//		expressionHandler.setRoleHierarchy(roleHierarchy());
//		return expressionHandler;
//	}
}
