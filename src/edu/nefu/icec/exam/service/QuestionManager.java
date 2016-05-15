package edu.nefu.icec.exam.service;

import java.util.List;

import edu.nefu.icec.exam.bean.QuestionBean;

public interface QuestionManager {

	/**
	 * 增加考试信息
	 * @param title 考试题目
	 * @param content 考试内容
	 * @param eid 考试id
	 * @return
	 */
	String add(String title,String content,String eid);
	
	/**
	 * 删除题目
	 * @param id 题目id
	 */
	void remove(String id);
	
	/**
	 * 得到问题
	 * @param id 问题id
	 * @return
	 */
	QuestionBean get(String id);
	
	/**
	 * 通过考试得到题目
	 * @param eid 考试id
	 * @return
	 */
	List<QuestionBean> getByExam(String eid);
	
	int getCountByExam(String eid);
	
	
	/**
	 * 得到问题
	 * @param aid 答案id
	 * @return
	 */
	QuestionBean getByAid(String aid);
}
