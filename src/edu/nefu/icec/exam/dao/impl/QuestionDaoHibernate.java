package edu.nefu.icec.exam.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.nefu.icec.common.hibernate3.support.ExamHibernateDaoSupport;
import edu.nefu.icec.exam.dao.QuestionDao;
import edu.nefu.icec.exam.domain.Question;

public class QuestionDaoHibernate extends ExamHibernateDaoSupport implements
		QuestionDao {

	@Override
	public Question get(String id) {
		return getHibernateTemplate().get(Question.class, id);
	}

	@Override
	public String save(Question question) {
		return (String) getHibernateTemplate().save(question);
	}

	@Override
	public void update(Question question) {
		getHibernateTemplate().merge(question);
	}

	@Override
	public void delete(Question question) {
		getHibernateTemplate().delete(question);
	}

	@Override
	public void delete(String id) {
		Question question = getHibernateTemplate().get(Question.class, id);
		getHibernateTemplate().delete(question);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findAll() {
		String hql = "from Question";
		return (List<Question>) getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getByExam(String eid) {
		String hql = "from Question where eid = '" + eid + "'";
		List<Question> question = getHibernateTemplate().find(hql);
		if (question.size() > 0) {
			 return question;
		} else {
			return null;
		}
	}

	@Override
	public int getCountByExam(String eid) {
		final String hql = "select count(*) from Question where eid = '"+eid+"'";
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
