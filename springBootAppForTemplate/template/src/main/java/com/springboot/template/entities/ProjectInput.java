package com.springboot.template.entities;


public class ProjectInput {

	private String groupId;
	private String artifactId;
	private String name;
	private String description;
	private String packaging;
	
	public ProjectInput() {
	}
	public ProjectInput(String groupId, String artifactId, String name, String description, String packaging) {
		
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.name = name;
		this.description = description;
		this.packaging = packaging;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPackaging() {
		return packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	@Override
	public String toString() {
		return "userEntity [groupId=" + groupId + ", artifactId=" + artifactId + ", name=" + name + ", description="
				+ description + ", packaging=" + packaging + "]";
	}
	
}
