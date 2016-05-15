package edu.nefu.icec.exam.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.nefu.icec.exam.bean.TeacherBean;

public interface TeacherManager {

	public static final String FLAG="teacher";
	
	/**
	 * 教师登录
	 * @param number
	 * @param password
	 * @return
	 */
	String login(String number,String password);
	
	List<TeacherBean> getAll();
	
	/**
	 * 增加教师
	 * @param number
	 * @param name
	 * @param password
	 * @return
	 */
	String add(String number,String name,String password);
	
	/**
	 * 修改老师信息
	 * @param number 老师职工号
	 * @param name 老师名
	 * @param password 密码
	 */
	void modify(String id,String number,String name,String password);
	
	/**
	 * 删除老师信息
	 * @param id 老师id
	 */
	void remove(String id);
	
	/**
	 * 得到老师信息
	 * @param id 老师id
	 * @return
	 */
	TeacherBean get(String id);
	
	/**
	 * 注册Session
	 * @param number 老师职工号
	 * @param session
	 * @return
	 */
	TeacherBean registSession(String number,HttpSession session);
	
	/**
	 * 移除用户Session
	 * @param session HttpSession
	 */
	void removeUserSession(HttpSession session);
	
	/**
	 * 检查当前页面Session是否过期
	 * @param session HttpSession
	 * @return 是否过期
	 */
	TeacherBean checkSession(HttpSession session);
	
	TeacherBean getByNumber(String number);
	
	String getTidByErid(String erid);
}
