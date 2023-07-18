package com.springboot.template.entities;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInput {

	private String groupId;
	private String artifactId;
	private String name;
	private String description;
	private String packaging;
	private Set<String> dependency;

}
