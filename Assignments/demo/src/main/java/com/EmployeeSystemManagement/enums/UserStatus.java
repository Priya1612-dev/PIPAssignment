package com.EmployeeSystemManagement.enums;

public enum UserStatus {
	ADMIN("Admin"),
	REGULAR("Regular");
	private String displayName;
	UserStatus(String displayName){
		this.displayName=displayName;
	}
	@Override
    public String toString() {
        return this.displayName;
    }
}
