package edu.nefu.icec.exam.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.nefu.icec.exam.bean.ExamBean;

public interface ExamManager {
	
	
	/**
	 * 得到学生信息
	 * @param id 学生id
	 * @return
	 */
	ExamBean get(String eid);
	
	/**
	 * 增加课程
	 * @param name 考试名称
	 * @param selected 选择的题目数
	 * @param session 老师的session
	 * @return
	 */
	String add(String name, int selected,HttpSession session);
	
	/**
	 * 修改考试信息
	 * @param id 考试id
	 * @param name 考试名
	 * @param selected 选择的题目数
	 */
	void modify(String id,String name,int selected);
	
	/**
	 * 删除考试信息
	 * @param id
	 */
	void remove(String id);
	
	/**
	 * 得到所有的考试信息
	 * @return
	 */
	List<ExamBean> getAll();
	
	/**
	 * 根据老师得到考试信息
	 * @param session 老师session
	 * @return
	 */
	List<ExamBean> getByTeacher(HttpSession session);
	
	/**
	 * 根据老师得到考试信息
	 * @param session 老师session
	 * @return
	 */
	List<ExamBean> getByTeacherID(String tid);
	
	/**
	 * 根据老师得到考试数量
	 * @param session 老师session
	 * @return
	 */
	int getCountByTeacherID(String tid);
	
	/**
	 * 修改考试状态
	 * @param eid 考试id
	 * @param enable 要修改成的考试状态
	 * @return
	 */
	boolean enable(String eid,boolean enable);
	
	/**
	 * 查看考试状态
	 * @param eid 考试id 
	 * @return
	 */
	boolean isEnable(String eid);
	
	/**
	 * 查询正在进行的考试
	 * @return
	 */
	List<ExamBean> getExamEnable();
	
}
