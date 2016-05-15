package edu.nefu.icec.exam.service;

public interface AdminManager {

	/**
	 * 管理员登录
	 * @param username 管理员用户名
	 * @param password 管理员密码
	 * @return
	 */
	boolean login(String username,String password);
}
