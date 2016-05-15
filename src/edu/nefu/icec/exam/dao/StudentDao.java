package edu.nefu.icec.exam.dao;

import java.util.List;

import edu.nefu.icec.exam.domain.Student;

public interface StudentDao {
	Student get(String id);

	String save(Student student);

	void update(Student student);

	void delete(Student student);

	void delete(String id);

	List<Student> findAll();
	
	/**
	 * 查找学生
	 * @param keyword 
	 * @return
	 */
	List<Student> search(String keyword);
	
	/**
	 * 通过学号得到学生
	 * @param number
	 * @return
	 */
	Student getByNumber(String number);
}
