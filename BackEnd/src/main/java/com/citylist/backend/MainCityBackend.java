package com.citylist.backend;

import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 ** @BMN 2021
 **
 **/
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.citylist.backend")
@org.springframework.data.jpa.repository.config.EnableJpaRepositories({ "com.citylist.backend.dao" })
@EntityScan("com.citylist.backend.entities")
public class MainCityBackend {
	public static void main(String[] args) {
		SpringApplicationBuilder managerApplicationBuilder = new SpringApplicationBuilder(MainCityBackend.class);
		managerApplicationBuilder.properties(getProperties());
		managerApplicationBuilder.run(args);
	}

	private static Properties getProperties() {
		Properties managerProperties = new Properties();
		managerProperties.put("spring.config.name", "citylist");
		return managerProperties;
	}
}
