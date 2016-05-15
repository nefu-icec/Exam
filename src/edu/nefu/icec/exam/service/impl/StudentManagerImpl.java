package edu.nefu.icec.exam.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.nefu.common.util.StudentRegistTool;
import edu.nefu.icec.exam.bean.StudentBean;
import edu.nefu.icec.exam.domain.Examer;
import edu.nefu.icec.exam.domain.Student;
import edu.nefu.icec.exam.service.StudentManager;
import edu.nefu.icec.exam.service.util.ManagerTemplate;

public class StudentManagerImpl extends ManagerTemplate implements
		StudentManager {

	@Override
	public List<StudentBean> getAll() {
		List<StudentBean> listBean = new ArrayList<StudentBean>();
		for (Student student : studentDao.findAll()) {
			listBean.add(new StudentBean(student));
		}
		return listBean;
	}

	@Override
	public List<StudentBean> search(String keyword) { 
		String nu = ""; 
		if (keyword != null) {
			nu = keyword;
		} 
		List<Student> list = studentDao.search(nu);
		return toBean(list); 
	}

	@Override
	public void modify(String id, String number, String name, String classname) {
		Student student = studentDao.get(id);
		student.setName(name);
		student.setNumber(number);
		student.setClassname(classname);
		studentDao.update(student);
	}

	@Override
	public void remove(String id) {
		List<Examer> list = examerDao.getBySid(id);
		for(Examer examer : list){
			examerDao.delete(examer);
		}
		studentDao.delete(id);
	}

	@Override
	public String addStudent(String number, String name, String classname) {
		Student student = new Student();
		student.setNumber(number);
		student.setName(name);
		student.setClassname(classname);
		return studentDao.save(student);
	}

	@Override
	public int addStudentByExcel(String excelPath) throws IOException {
		int result = 0;
		StudentRegistTool srt = new StudentRegistTool();
		List<Student> list = srt.readXls(excelPath);
		if (list != null) {
			for (Student student : list) {
				// if (studentDao.getByNumber(student.getNumber()) == null)
				try {
					addStudent(student.getNumber(), student.getName(),student.getClassname());
					result++;
				} catch (Exception e) { 
					System.out.println(e.getMessage());
				}
			}
		}
		System.out.println(result);
		return result;
	}

	@Override
	public StudentBean get(String id) {
		Student student = studentDao.get(id);
		return new StudentBean(student);
	}

	@Override
	public StudentBean getByExamerId(String erid) { 
		return new StudentBean(examerDao.get(erid).getStudent());
	}

	@Override
	public StudentBean getByNumber(String number) {
		return new StudentBean(studentDao.getByNumber(number));
	}

}
