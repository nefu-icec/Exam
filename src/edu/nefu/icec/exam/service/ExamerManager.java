package edu.nefu.icec.exam.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.nefu.icec.exam.bean.ExamerBean;

public interface ExamerManager {
	public static final String FLAG="examer";
	
	/**
	 * 新增考生
	 * @param id 考生id
	 * @param eid 考试id
	 * @return
	 */
	public String addExamer(String id,String eid);
	
	/**
	 * 批量新增考生
	 * @param id 考生id
	 * @param eid 考试id
	 * @return
	 */
	public List<String> addExamers(List<String> id,String eid);
	
	/**
	 * 得到一个考生
	 * @param id 考生id
	 * @return
	 */
	public ExamerBean get(String id);
	
	/**
	 * 通过考试得到考生
	 * @param id 考试id
	 * @return
	 */
	public List<ExamerBean> getByExam(String eid);
	
	/**
	 * 搜索学生
	 * @param eid 考试id
	 * @param keyword 搜索关键字
	 * @return
	 */
	public List<ExamerBean> search(String eid,String keyword);
	
	/**
	 * 删除考生信息
	 * @param id 考生id
	 */
	public void remove(String id);
	
	/**
	 * 考生登录
	 * @param number
	 * @param password
	 * @return
	 */
	public String login(String number,String password,String eid);
	
	/**
	 * 注册session
	 * @param number
	 * @param session
	 * @return
	 */
	public ExamerBean registSession(String number, String eid,HttpSession session);
	
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
	ExamerBean checkSession(HttpSession session);
	
	/**
	 * 得到指定考试的考生数
	 * @param eid
	 * @return
	 */
	public int getExamerCount(String eid);
	
	/**
	 * 下载考生信息
	 * @param downloadPath
	 * @return
	 */
	public String uploadExamerInfo(String downloadPath,String eid);
	
	/**
	 * 计算成绩
	 */
	public double jiSuanMark(String erid); 
	
	/**
	 * 通过学生id得到考生
	 * @param id 学生id
	 * @return
	 */
	public List<ExamerBean> getBySid(String sid);
	
	 /**
	  * 通过学生id 和考试id查找考生
	  * @param sid
	  * @param eid
	  * @return
	  */
	 ExamerBean getExamerBySidAndEid(String sid,String eid);
}
