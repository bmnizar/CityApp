package com.citylist.backend.core;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.web.client.RestTemplate;

import com.citylist.backend.mapstruct.MapStructMapper;

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

	private static MapStructMapper mapStructMapper;

	@Autowired
	public CityListConfig(ApplicationContext ac) {
		mapStructMapper = ac.getBean(MapStructMapper.class);
	}

	public static MapStructMapper getMapStructMapper() {
		return mapStructMapper;
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
