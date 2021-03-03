package com.cos.mycontroller;

public class User {
	
	private String username;
	private String password;
	
	// 1. 필드 확인
	public String getUsername() {
		return username;
	}
	
	// 2. setter 호츨
	public void setUsername(String username) {
		System.out.println("setUsername() 실행됨");
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("setPassword() 실행됨");
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}



