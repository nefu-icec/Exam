package edu.nefu.icec.exam.dao;

import java.util.List;

import edu.nefu.icec.exam.domain.Examer;

public interface ExamerDao {
	Examer get(String id);

	String save(Examer examer);

	void update(Examer examer);

	void delete(Examer examer);

	void delete(String id);

	List<Examer> findAll();
	
	/**
	 * 通过考试查考生
	 * @param eid
	 * @return
	 */
	List<Examer> getByExam(String eid);
	
	/**
	 * 通过关键字查找
	 * @param eid
	 * @param keyword
	 * @return
	 */
	List<Examer> getByKeyword(String eid,String keyword);
	
	/**
	 * 通过学生查考生
	 * @param sid
	 * @return
	 */
	List<Examer> getBySid(String sid);
	
	/**
	 * 得到指定考试的考生数
	 * @param eid
	 * @return
	 */
	 int getExamerCount(String eid); 
	 
	 /**
	  * 通过学生id 和考试id查找考生
	  * @param sid
	  * @param eid
	  * @return
	  */
	 Examer getExamerBySidAndEid(String sid,String eid);
}
