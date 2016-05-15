package edu.nefu.icec.exam.dao.impl;

import java.util.Iterator;
import java.util.List;

import edu.nefu.icec.common.hibernate3.support.ExamHibernateDaoSupport;
import edu.nefu.icec.exam.dao.ExamDao;
import edu.nefu.icec.exam.domain.Exam;
import edu.nefu.icec.exam.domain.Examer;
import edu.nefu.icec.exam.domain.Question;
import edu.nefu.icec.exam.domain.Teacher;

public class ExamDaoHibernate extends ExamHibernateDaoSupport implements
		ExamDao {

	@Override
	public Exam get(String id) {
		return getHibernateTemplate().get(Exam.class, id);
	}

	@Override
	public String save(Exam exam) {
		return (String) getHibernateTemplate().save(exam);
	}

	@Override
	public void update(Exam exam) {
		getHibernateTemplate().merge(exam);
	}

	@Override
	public void delete(Exam exam) {
		getHibernateTemplate().delete(exam);
	}

	@Override
	public void delete(String id) {
		Exam exam = getHibernateTemplate().get(Exam.class, id);
		getHibernateTemplate().delete(exam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> findAll() {
		return (List<Exam>) getHibernateTemplate().find("from Exam");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> findByTeacher(Teacher teacher) {
		String hql = "from Exam where tid = '"+teacher.getTid()+"'" ;
		return ((List<Exam>) getHibernateTemplate().find(hql));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> findExamEnable() { 
		String hql = "from Exam where  enable = '"+1+"'" ;
		return ((List<Exam>) getHibernateTemplate().find(hql));
	}

	@Override
	public Exam getAllExamInfo(String id) { 
		Exam exam = getHibernateTemplate().get(Exam.class, id); 
		Iterator<Question> it1 = exam.getQuestions().iterator();
		while(it1.hasNext()){
			System.out.println(it1.next().getContent());
		}
		Iterator<Examer> it2 = exam.getExamers().iterator();
		while(it2.hasNext()){
			System.out.println(it2.next().getPassword());
		} 
		return exam;
	}

}

