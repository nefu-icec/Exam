package edu.nefu.icec.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import edu.nefu.icec.exam.bean.TeacherBean;
import edu.nefu.icec.exam.domain.Answer;
import edu.nefu.icec.exam.domain.Exam;
import edu.nefu.icec.exam.domain.Question;
import edu.nefu.icec.exam.domain.Teacher;
import edu.nefu.icec.exam.service.TeacherManager;
import edu.nefu.icec.exam.service.util.ManagerTemplate;

public class TeacherManagerImpl extends ManagerTemplate implements
		TeacherManager {

	@Override
	public String login(String number, String password) {
		Teacher teacher = teacherDao.getByNumber(number);
		if (teacher != null && password.equals(teacher.getPassword()))
			return teacher.getTid();
		return null;
	}

	@Override
	public List<TeacherBean> getAll() {
		List<TeacherBean> listBeans = new ArrayList<TeacherBean>();
		for (Teacher teacher : teacherDao.findAll()) {
			listBeans.add(new TeacherBean(teacher, teacherDao
					.getExamCount(teacher.getTid())));
		}
		return listBeans;
	}

	@SuppressWarnings("finally")
	@Override
	public String add(String number, String name, String password) {
		Teacher teacher = new Teacher();
		teacher.setNumber(number);
		teacher.setName(name);
		teacher.setPassword(password);
		String result = null;
		try {
			result = teacherDao.save(teacher);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return result;
		}
	}

	@Override
	public void modify(String id, String number, String name, String password) {
		Teacher teacher = teacherDao.get(id);
		teacher.setNumber(number);
		teacher.setName(name);
		teacher.setPassword(password);
		teacherDao.update(teacher);
	}

	@Override
	public void remove(String id) {
		Teacher teacher = teacherDao.get(id);
		if (examDao.findByTeacher(teacher) != null) {
			for (Exam exam : examDao.findByTeacher(teacher)) {
				if (questionDao.getByExam(exam.getEid()) != null) {
					for (Question question : questionDao.getByExam(exam
							.getEid())) {
						for (Answer answer : answerDao
								.findAnswerByQuestion(question.getQid())) {
							answerDao.delete(answer);
						}
						questionDao.delete(question);
					}
				}
				examDao.delete(exam.getEid());
			}
		}
		teacherDao.delete(id);
	}

	@Override
	public TeacherBean get(String id) {
		Teacher teacher = teacherDao.get(id);
		return new TeacherBean(teacher, teacherDao.getExamCount(id));
	}

	@Override
	public TeacherBean registSession(String number, HttpSession session) {
		Teacher teacher = teacherDao.getByNumber(number);
		if (teacher != null) {
			TeacherBean teacherBean = new TeacherBean(teacher,
					teacherDao.getExamCount(teacher.getTid()));
			session.setAttribute(FLAG, teacherBean);
			return teacherBean;
		}
		return null;
	}

	@Override
	public void removeUserSession(HttpSession session) {
		session.removeAttribute(FLAG);
	}

	@Override
	public TeacherBean checkSession(HttpSession session) {
		if (session.getAttribute(FLAG) == null)
			return null;
		else
			return (TeacherBean) session.getAttribute(FLAG);
	}

	@Override
	public TeacherBean getByNumber(String number) {
		Teacher teacher = teacherDao.getByNumber(number);
		return new TeacherBean(teacher, teacherDao.getExamCount(teacher
				.getTid()));
	}

	@Override
	public String getTidByErid(String erid) {
		return examerDao.get(erid).getExam().getTeacher().getTid();
	}
}
