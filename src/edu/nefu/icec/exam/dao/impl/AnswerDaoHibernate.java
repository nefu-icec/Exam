package edu.nefu.icec.exam.dao.impl;

import java.util.List;

import edu.nefu.icec.common.hibernate3.support.ExamHibernateDaoSupport;
import edu.nefu.icec.exam.dao.AnswerDao;
import edu.nefu.icec.exam.domain.Answer;

public class AnswerDaoHibernate extends ExamHibernateDaoSupport implements
		AnswerDao {

	@Override
	public Answer get(String id) {
		return getHibernateTemplate().get(Answer.class, id);
	}

	@Override
	public String save(Answer answer) {
		return (String) getHibernateTemplate().save(answer);
	}

	@Override
	public void update(Answer answer) {
		getHibernateTemplate().merge(answer);
	}

	@Override
	public void delete(Answer answer) {
		getHibernateTemplate().delete(answer);
	}

	@Override
	public void delete(String id) {
		Answer answer = getHibernateTemplate().get(Answer.class, id);
		getHibernateTemplate().delete(answer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> findAll() {
		String hql = "from Answer";
		return (List<Answer>) getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> findAnswerByExamer(String erid) {
		String hql = "from Answer where erid = '"+erid+"' order by aid asc";
		return (List<Answer>) getHibernateTemplate().find(hql);
//		String sql = "select * from answer where erid = '"+erid+"' order by aid asc";
//		return getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql).addEntity(Answer.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> findAnswerByQuestion(String qid) {  
		String hql = "from Answer where qid = '"+qid+"'";
		return (List<Answer>) getHibernateTemplate().find(hql);
	}

}
