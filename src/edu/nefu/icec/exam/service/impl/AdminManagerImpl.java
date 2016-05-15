package edu.nefu.icec.exam.service.impl;

import edu.nefu.icec.exam.service.AdminManager;

public class AdminManagerImpl implements AdminManager {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AdminManagerImpl(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AdminManagerImpl() {
		super();
	}

	@Override
	public boolean login(String username, String password) {
		if(username.equals(this.username)&&password.equals(this.password))
			return true;
		return false;
	}

}
