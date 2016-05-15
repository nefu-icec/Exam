package edu.nefu.icec.exam.dao;

import java.util.List;

import edu.nefu.icec.exam.domain.Exam;
import edu.nefu.icec.exam.domain.Teacher;

public interface ExamDao {
	Exam get(String id);
	
	Exam getAllExamInfo(String id);

	String save(Exam exam);

	void update(Exam exam);

	void delete(Exam exam);

	void delete(String id);

	List<Exam> findAll();
	
	/**
	 * 通过老师找考试
	 * @return
	 */
	List<Exam> findByTeacher(Teacher teacher);
	
	/**
	 * 返回正在进行的考试
	 * @return
	 */
	List<Exam> findExamEnable();
	
}
