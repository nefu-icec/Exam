package edu.nefu.icec.exam.dao;

import java.util.List;

import edu.nefu.icec.exam.domain.Question;

public interface QuestionDao {
	Question get(String id);

	String save(Question question);

	void update(Question question);

	void delete(Question question);

	void delete(String id);

	List<Question> findAll();
	
	List<Question> getByExam(String eid); 
	
	int getCountByExam(String eid); 
	
}
