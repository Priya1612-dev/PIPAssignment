package com.springboot.template.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MapUtil {
	
	public static Map<String,List<String>> getDependencyMap() {
		Map<String,List<String>> map = new LinkedHashMap<>();
		map.put("Spring Web", 
				Arrays.asList("org.springframework.boot",
						"spring-boot-starter-web"));
		map.put("Spring GraphQL", 
				Arrays.asList("org.springframework.boot",
						"spring-boot-starter-graphql"));
		map.put("Thymeleaf", 
				Arrays.asList("org.springframework.boot",
						"spring-boot-starter-thymeleaf"));
		map.put("Spring Security", 
				Arrays.asList("org.springframework.boot",
						"spring-boot-starter-security"));
		map.put("Spring Data Jpa", 
				Arrays.asList("org.springframework.boot",
						"spring-boot-starter-data-jpa"));
		map.put("Spring Data JDBC", 
				Arrays.asList("org.springframework.boot",
						"spring-boot-starter-data-jdbc"));
		map.put("MySQL Driver", 
				Arrays.asList("com.mysql",
						"mysql-connector-j"));
		map.put("H2 Database", 
				Arrays.asList("com.h2database",
						"h2"));
		map.put("Validation", 
				Arrays.asList("org.springframework.boot",
						"spring-boot-starter-validation"));
		map.put("Lombok", 
				Arrays.asList("org.projectlombok",
						"lombok"));

		return map;
	}

}
