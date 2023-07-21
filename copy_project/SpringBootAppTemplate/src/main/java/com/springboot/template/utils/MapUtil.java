package com.springboot.template.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MapUtil {
	private MapUtil() {
	}

	public static Map<String,List<String>> getDependencyMap() {
		String springBootGroupId="org.springframework.boot";
		Map<String,List<String>> map = new LinkedHashMap<>();
		map.put("Spring Web", 
				Arrays.asList(springBootGroupId,
						"spring-boot-starter-web"));
		map.put("Spring GraphQL", 
				Arrays.asList(springBootGroupId,
						"spring-boot-starter-graphql"));
		map.put("Thymeleaf", 
				Arrays.asList(springBootGroupId,
						"spring-boot-starter-thymeleaf"));
		map.put("Spring Security", 
				Arrays.asList(springBootGroupId,
						"spring-boot-starter-security"));
		map.put("Spring Data Jpa", 
				Arrays.asList(springBootGroupId,
						"spring-boot-starter-data-jpa"));
		map.put("Spring Data JDBC", 
				Arrays.asList(springBootGroupId,
						"spring-boot-starter-data-jdbc"));
		map.put("MySQL Driver", 
				Arrays.asList("com.mysql",
						"mysql-connector-j"));
		map.put("H2 Database", 
				Arrays.asList("com.h2database",
						"h2"));
		map.put("Validation", 
				Arrays.asList(springBootGroupId,
						"spring-boot-starter-validation"));
		map.put("Lombok", 
				Arrays.asList("org.projectlombok",
						"lombok"));

		return map;
	}

}
