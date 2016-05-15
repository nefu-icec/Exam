package edu.nefu.icec.exam.dao;

import java.util.List;

import edu.nefu.icec.exam.domain.Answer;
  
public interface AnswerDao {
	Answer get(String id);

	String save(Answer answer);

	void update(Answer answer);

	void delete(Answer answer);

	void delete(String id);

	List<Answer> findAll();
	
	/**
	 * 通过考生id得到答案
	 * @param erid
	 * @return
	 */
	List<Answer> findAnswerByExamer(String erid);
	
	/**
	 * 通过问题id得到答案
	 * @param qid
	 * @return
	 */
	List<Answer> findAnswerByQuestion(String qid);
}
