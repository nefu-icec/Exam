package edu.nefu.icec.exam.service;

import java.io.IOException;
import java.util.List;

import edu.nefu.icec.exam.bean.StudentBean;

public interface StudentManager {

	/**
	 * 得到所有的学生
	 * @return
	 */
	List<StudentBean> getAll();
	
	/**
	 * 按照条件查找学生
	 * @param keyword 查询关键字
	 */
	List<StudentBean> search(String keyword);
	
	/**
	 * 修改学生信息
	 * @param id 学生id
	 * @param number 学生学号
	 * @param name 学生姓名
	 * @param classname 班级名
	 */
	void modify(String id,String number,String name,String classname);
	
	/**
	 * 删除学生
	 * @param id 学生id
	 */
	void remove(String id);
	
	String addStudent(String number,String name,String classname);
	
	/**
	 * 通过Excel注册学生
	 * @param excelPath
	 * @return
	 * @throws IOException 
	 */
	public int addStudentByExcel(String excelPath) throws IOException;
	
	/**
	 * 得到学生信息
	 * @param id 学生id
	 * @return
	 */
	StudentBean get(String id);
	
	/**
	 * 通过考生ID得到学生信息
	 * @param id 学生id
	 * @return
	 */
	StudentBean getByExamerId(String erid);
	
	
	/** 
	 * @param id 学生id
	 * @return
	 */
	StudentBean getByNumber(String number);
	
}
