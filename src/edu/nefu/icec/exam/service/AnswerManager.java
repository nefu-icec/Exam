package edu.nefu.icec.exam.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.nefu.icec.exam.bean.AnswerBean;

public interface AnswerManager { 
	
	/**
	 * 创建或者得到问题
	 * @param examerid
	 * @return
	 */
	public List<AnswerBean> createOrGet(String examerid);
	
	/**
	 * 获得问题
	 * @param id 问题id
	 * @return
	 */
	public AnswerBean get(String id);
	
	/**
	 * 修改问题
	 * @param id 问题id
	 * @param description
	 */
	void modify(String id,String description);
	
	/**
	 * 给分数
	 * @param id 问题id
	 * @param mark 分数
	 */
	void giveMark(String id,double mark);
	
	/**
	 * 得到所有的考试
	 * @param erid
	 * @return
	 */
	public List<AnswerBean>  findAnswerByExamerId(String erid); 
	
	/**
	 * 得到考生的答案
	 * @param session
	 * @return
	 */
	public List<AnswerBean> findAnswerByExamer(HttpSession session); 
	
	/**
	 * 更新答案状态
	 * @param aid
	 * @param state
	 */
	public void updateAnswerState(String aid , int state);
	
}
