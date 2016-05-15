package edu.nefu.icec.exam.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import edu.nefu.common.util.ExamerInfo;
import edu.nefu.icec.exam.bean.ExamerBean;
import edu.nefu.icec.exam.domain.Answer;
import edu.nefu.icec.exam.domain.Exam;
import edu.nefu.icec.exam.domain.Examer;
import edu.nefu.icec.exam.domain.Student;
import edu.nefu.icec.exam.service.ExamerManager;
import edu.nefu.icec.exam.service.util.ManagerTemplate;

public class ExamerManagerImpl extends ManagerTemplate implements ExamerManager {

	@Override
	public String addExamer(String id, String eid) { 
		Examer examer1 = null; 
		String result =null;
		boolean flag = false;
		if (examerDao.getBySid(id) != null) {
			List<Examer> list = examerDao.getBySid(id);
			for(Examer examer : list){
				examer1 =examer;
				if(examer.getExam().getEid().equals(eid)){
					flag = true;
					break;
				}
			} 
		}
		if (examer1 == null || !flag) { 
			try{ 
			Random random = new Random();
			Integer x = random.nextInt(899999);  
			x = x+100000;
			String password = x.toString();
			Exam exam = examDao.get(eid);
			Student student = studentDao.get(id);
			Examer examer = new Examer();
			examer.setExam(exam);
			examer.setPassword(password);
			examer.setStudent(student);
			examer.setMark(0.0); 
			examer.setHaslogin(false);
			examer.setAnswers(new HashSet<Answer>());
			result = examerDao.save(examer); 		
		}
			catch(Exception e){ 
				System.out.println(e.getMessage());
			}
			return result;
		} else {
			return null;
		}
	}

	@Override
	public List<String> addExamers(List<String> id, String eid) {
		for (String i : id) {
			addExamer(i, eid);
		}
		return id;
	}

	@Override
	public ExamerBean get(String id) {
		if(examerDao.get(id)!=null)
		return new ExamerBean(examerDao.get(id));
		return null;
	}

	@Override
	public List<ExamerBean> getByExam(String eid) {
		List<ExamerBean> liBeans = new ArrayList<ExamerBean>();
		for (Examer examer : examerDao.getByExam(eid)) {
			liBeans.add(new ExamerBean(examer));
		}
		return liBeans;
	}

	@Override
	public List<ExamerBean> search(String eid, String keyword) {
		List<ExamerBean> liBeans = new ArrayList<ExamerBean>();
		for (Examer examer : examerDao.getByKeyword(eid, keyword)) {
			liBeans.add(new ExamerBean(examer));
		}
		return liBeans;
	}

	@Override
	public void remove(String id) {
		List<Answer> answers = answerDao.findAnswerByExamer(id);
		for(Answer answer : answers){
			answerDao.delete(answer);
		}
		examerDao.delete(id);
	}

	@Override
	public String login(String number, String password,String eid) { 
		String result = null; 
		if(studentDao.getByNumber(number)!=null){
		Student student = studentDao.getByNumber(number);
		Examer examer = examerDao.getExamerBySidAndEid(student.getSid(), eid);
		if(examer!=null){
			result = examer.getErid();
		} 
		}
		return result;
	}

	@Override
	public ExamerBean registSession(String number, String eid , HttpSession session) {
		Examer examerResult = null; 
		if(studentDao.getByNumber(number)!=null){
			Student student = studentDao.getByNumber(number);
			Examer examer = examerDao.getExamerBySidAndEid(student.getSid(), eid);
			if(examer!=null){ 
				examerResult=examer;
			} 
			}
		if (examerResult != null) {
			ExamerBean examerBean = new ExamerBean(examerResult);
			session.setAttribute(FLAG, examerBean);
			return examerBean;
		}
		return null;
	}

	@Override
	public void removeUserSession(HttpSession session) {
		session.removeAttribute(FLAG);
	}

	@Override
	public ExamerBean checkSession(HttpSession session) {
		if(session.getAttribute(FLAG)==null)
			return null;
		else
			return (ExamerBean)session.getAttribute(FLAG);
	}

	@Override
	public int getExamerCount(String eid) {
		return examerDao.getExamerCount(eid);
	}

	@Override
	public String uploadExamerInfo(String downloadPath,String eid) {
		ExamerInfo ei = new ExamerInfo();
		List<ExamerBean> list = getByExam(eid);
		return ei.downloadExamer(downloadPath, list);
	}

	@Override
	public double jiSuanMark(String erid) {
		Examer examer = examerDao.get(erid);
		Set<Answer> set = examer.getAnswers();
		double result = 0;
		for(Answer answer:set){
			result+=answer.getMark();
		}
		examer.setMark(result);
		examerDao.update(examer);
		return result;
	}

	@Override
	public List<ExamerBean> getBySid(String sid) {
		List<ExamerBean> listBeans = new ArrayList<ExamerBean>();
		for(Examer examer:examerDao.getBySid(sid)){
			listBeans.add(new ExamerBean(examer));
		}
		return listBeans;
	}

	@Override
	public ExamerBean getExamerBySidAndEid(String sid, String eid) {
		return new ExamerBean(examerDao.getExamerBySidAndEid(sid, eid));
	}
 

}
