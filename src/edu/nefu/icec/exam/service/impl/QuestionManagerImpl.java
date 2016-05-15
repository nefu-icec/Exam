package edu.nefu.icec.exam.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.nefu.icec.exam.bean.QuestionBean;
import edu.nefu.icec.exam.domain.Exam;
import edu.nefu.icec.exam.domain.Question;
import edu.nefu.icec.exam.service.QuestionManager;
import edu.nefu.icec.exam.service.util.ManagerTemplate;

public class QuestionManagerImpl extends ManagerTemplate implements
		QuestionManager {

	@Override
	public String add(String title, String content, String eid) {
		Question question = new Question();
		String result = null;
		try {
			// 保存问题
//			Exam exam1 = examDao.get(eid);
//			question.setContent(content);
//			question.setTitle(title);
//			question.setExam(exam1);
//			result = questionDao.save(question);
			 
			Exam exam = examDao.get(eid);
			Set<Question> set = exam.getQuestions();
			question.setContent(content);
			question.setTitle(title);  
			set.add(question);
			result = questionDao.save(question); 
		
			exam.setQuestions(set);
			examDao.update(exam);
			
		} catch (Exception e) {
		}

		// 返回值
		return result;
	}

	@Override
	public void remove(String id) {
		try {
			questionDao.delete(id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public QuestionBean get(String id) { 
		try{
		Question question = questionDao.get(id);
		return new QuestionBean(question);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<QuestionBean> getByExam(String eid) {
		List<QuestionBean> beans = null;
		try {
			beans = new ArrayList<QuestionBean>();
			for (Question q : questionDao.getByExam(eid)) {
				beans.add(new QuestionBean(q));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				return beans;
			} catch (Exception e) {
			}
		}
		return beans;
	}

	@Override
	public int getCountByExam(String eid) {
		return questionDao.getCountByExam(eid);
	}

	@Override
	public QuestionBean getByAid(String aid) {
		return new QuestionBean(answerDao.get(aid).getQuestion());
	}

}
