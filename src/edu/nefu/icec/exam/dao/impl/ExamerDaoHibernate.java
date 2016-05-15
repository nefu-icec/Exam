package edu.nefu.icec.exam.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.nefu.icec.common.hibernate3.support.ExamHibernateDaoSupport;
import edu.nefu.icec.exam.dao.ExamerDao;
import edu.nefu.icec.exam.domain.Examer;

public class ExamerDaoHibernate extends ExamHibernateDaoSupport implements
		ExamerDao {

	@Override
	public Examer get(String id) {
		return getHibernateTemplate().get(Examer.class, id);
	}

	@Override
	public String save(Examer examer) {
		return (String) getHibernateTemplate().save(examer);
	}

	@Override
	public void update(Examer examer) {
		getHibernateTemplate().merge(examer);
	}

	@Override
	public void delete(Examer examer) {
		getHibernateTemplate().delete(examer);
	}

	@Override
	public void delete(String id) {
		Examer examer = getHibernateTemplate().get(Examer.class, id);
		getHibernateTemplate().delete(examer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Examer> findAll() {
		return (List<Examer>) getHibernateTemplate().find("from Examer");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Examer> getByExam(String eid) {
		String sql = "from Examer where eid = '" + eid + "'";
		List<Examer> list = (List<Examer>) getHibernateTemplate().find(sql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Examer> getByKeyword(String eid, String keyword) {
		String sql = "from Examer where eid = '" + eid + "'";
		List<Examer> list = (List<Examer>) getHibernateTemplate().find(sql);
		List<Examer> listResult = new ArrayList<Examer>();
		Iterator<Examer> it = list.iterator();
		while (it.hasNext()) {
			Examer examer = it.next();
			String stuInf = examer.getErid()
					+ examer.getStudent().getClassname()
					+ examer.getStudent().getName()
					+ examer.getStudent().getNumber()
					+ examer.getStudent().getSid();
			if (stuInf.contains(keyword)) {
				listResult.add(examer);
			}
		}
		return listResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Examer> getBySid(String sid) {
		String sql = "from Examer where sid = '" + sid + "'"; 
		return (List<Examer>) getHibernateTemplate().find(sql);
	}
	
	@Override
	public int getExamerCount(String eid) {
		final String hql = "select count(*) from Examer where eid = '"+eid+"'";
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

	@SuppressWarnings("unchecked")
	@Override
	public Examer getExamerBySidAndEid(String sid, String eid) {
		Examer result = null;
		String sql = "select * from examer where sid = '"+sid+"' and eid = '"+eid+"'";
		List<Examer> examers = (List<Examer>)getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql).addEntity(Examer.class).list();
		if(examers.size()>0){
			result = examers.get(0);
		}
		return result;
	}

}
