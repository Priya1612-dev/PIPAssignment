package com.EmployeeSystemManagement.enums;

public enum UserType {
	ACTIVE("Active"),
	INACTIVE("Inactive");
	private String displayName;
	UserType(String displayName){
		this.displayName=displayName;
	}
	@Override
    public String toString() {
        return this.displayName;
    }
}
