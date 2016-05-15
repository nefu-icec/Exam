package edu.nefu.icec.exam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nefu.icec.common.hibernate3.support.ExamHibernateDaoSupport;
import edu.nefu.icec.exam.dao.StudentDao;
import edu.nefu.icec.exam.domain.Student;

public class StudentDaoHibernate extends ExamHibernateDaoSupport implements
		StudentDao {

	@Override
	public Student get(String id) {
		return getHibernateTemplate().get(Student.class, id);
	}

	@Override
	public String save(Student student) {
		return (String) getHibernateTemplate().save(student);
	}

	@Override
	public void update(Student student) {
		getHibernateTemplate().merge(student);
	}

	@Override
	public void delete(Student student) {
		getHibernateTemplate().delete(student);
	}

	@Override
	public void delete(String id) {
		Student student = getHibernateTemplate().get(Student.class, id);
		System.out.println(student.getName());
		getHibernateTemplate().delete(student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAll() {
		String hql = "from Student" ;
		return (List<Student>) getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> search(String keyword) { 
		List<Student> list = new ArrayList<Student>();
		List<Student> listResult = new ArrayList<Student>();
		String hql = "from Student";
		list = getHibernateTemplate().find(hql);
		for(Student student : list){
			String studentInfo = student.getClassname()+student.getName()+student.getNumber()+student.getSid(); 
			if(studentInfo.contains(keyword)){
				listResult.add(student);
			}
		}
		return listResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getByNumber(String number) {
		String hql = "from Student where number = '"+number+"'";
		List<Student> list = getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
