package edu.nefu.icec.exam.dao;

import java.util.List;

import edu.nefu.icec.exam.domain.Teacher;

public interface TeacherDao {
	Teacher get(String id);

	String save(Teacher teacher);

	void update(Teacher teacher);

	void delete(Teacher teacher);

	void delete(String id);

	List<Teacher> findAll();
	
	Teacher getByNumber(String number);
	
	int getExamCount(String number); 
}
