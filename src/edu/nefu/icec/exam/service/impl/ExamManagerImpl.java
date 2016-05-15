package edu.nefu.icec.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import edu.nefu.icec.exam.bean.ExamBean;
import edu.nefu.icec.exam.bean.TeacherBean;
import edu.nefu.icec.exam.domain.Answer;
import edu.nefu.icec.exam.domain.Exam;
import edu.nefu.icec.exam.domain.Question;
import edu.nefu.icec.exam.domain.Teacher;
import edu.nefu.icec.exam.service.ExamManager;
import edu.nefu.icec.exam.service.TeacherManager;
import edu.nefu.icec.exam.service.util.ManagerTemplate;

public class ExamManagerImpl extends ManagerTemplate implements ExamManager {

	@Override
	public String add(String name, int selected, HttpSession session) {
		Exam exam = new Exam();
		exam.setName(name);
		exam.setSelected(selected);
		exam.setEnable(false); 
		exam.setUpload(new Date(System.currentTimeMillis()));
		TeacherBean teacherBean = (TeacherBean) session
				.getAttribute(TeacherManager.FLAG);
		Teacher teacher = teacherDao.get(teacherBean.getId());
		exam.setTeacher(teacher);
		exam.setQuestions(new HashSet<Question>()); 
		return examDao.save(exam);
	}

	@Override
	public void modify(String id, String name, int selected) {
		Exam exam = examDao.get(id);
		exam.setName(name);
		exam.setSelected(selected);
		exam.setUpload(new Date(System.currentTimeMillis()));
		examDao.update(exam);
	}

	@Override
	public void remove(String id) {
		List<Question> list = questionDao.getByExam(id);
		if(list!=null){
		for(Question question : list){
			for(Answer answer : answerDao.findAnswerByQuestion(question.getQid())){
				answerDao.delete(answer);
			}
			questionDao.delete(question);
		}
		}
		examDao.delete(id);
	}

	@Override
	public List<ExamBean> getAll() {
		List<ExamBean> examBeans = new ArrayList<ExamBean>();
		for (Exam exam : examDao.findAll()) {
			examBeans.add(new ExamBean(exam));
		}
		return examBeans;
	}

	@Override
	public List<ExamBean> getByTeacher(HttpSession session) {
		List<ExamBean> examBeans = new ArrayList<ExamBean>();
		TeacherBean teacherBean = (TeacherBean) session
				.getAttribute(TeacherManager.FLAG);
		Teacher teacher = teacherDao.get(teacherBean.getId());
		for (Exam exam : examDao.findByTeacher(teacher)) {
			examBeans.add(new ExamBean(exam));	
		}
		return examBeans;
	}

	@Override
	public boolean enable(String eid, boolean enable) {
		Exam exam = examDao.get(eid);
		exam.setEnable(enable);
		examDao.update(exam);
		return enable;
	}

	@Override
	public boolean isEnable(String eid) {
		Exam exam = examDao.get(eid);
		return exam.isEnable();
	}

	@Override
	public List<ExamBean> getByTeacherID(String tid) {
		List<ExamBean> examBeans = new ArrayList<ExamBean>();
		Teacher teacher = null;
		if (teacherDao.get(tid) != null) {
			teacher = teacherDao.get(tid);
			for (Exam exam : examDao.findByTeacher(teacher)) {
				examBeans.add(new ExamBean(exam));
			}
		}
		return examBeans;
	}

	@Override
	public int getCountByTeacherID(String tid) {
		return teacherDao.getExamCount(tid);
	}

	@Override
	public List<ExamBean> getExamEnable() {
		List<ExamBean>listBeans = new ArrayList<ExamBean>();
		for(Exam exam : examDao.findExamEnable()){
			listBeans.add(new ExamBean(exam));
		}
		return listBeans;
	}

	@Override
	public ExamBean get(String eid) {
		Exam exam = examDao.get(eid);
		return new ExamBean(exam);
	}
  
}
