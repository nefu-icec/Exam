package edu.nefu.icec.exam.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.nefu.icec.common.hibernate3.support.ExamHibernateDaoSupport;
import edu.nefu.icec.exam.dao.TeacherDao;
import edu.nefu.icec.exam.domain.Teacher;

public class TeacherDaoHibernate extends ExamHibernateDaoSupport implements
		TeacherDao {

	@Override
	public Teacher get(String id) {
		return getHibernateTemplate().get(Teacher.class, id);
	}

	@Override
	public String save(Teacher teacher) {
		return (String) getHibernateTemplate().save(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		getHibernateTemplate().update(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
		getHibernateTemplate().delete(teacher);
	}

	@Override
	public void delete(String id) {
		Teacher teacher = getHibernateTemplate().get(Teacher.class, id);
		getHibernateTemplate().delete(teacher);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> findAll() {
		String hql = "from Teacher";
		return (List<Teacher>) getHibernateTemplate().find(hql);
	}

	@Override
	public Teacher getByNumber(String number) {
		String hql = "from Teacher where number = ? ";
		int a =getHibernateTemplate().find(hql,number).size();
		if(a>0){
		return  (Teacher) getHibernateTemplate().find(hql,number).get(0);
		} 
		else{
			return null;
		}
	}

	@Override
	public int getExamCount(String number) {
		final String hql = "select count(*) from Exam where tid = '"+number+"'";
		int count = getHibernateTemplate().execute(
				new HibernateCallback<Long>() {
					@Override
					public Long doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						return (long) query.uniqueResult();
					}
				}).intValue();
		return count;
	} 	
}

