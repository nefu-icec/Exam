package edu.nefu.icec.exam.service.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nefu.icec.exam.bean.StudentBean;
import edu.nefu.icec.exam.dao.AnswerDao;
import edu.nefu.icec.exam.dao.ExamDao;
import edu.nefu.icec.exam.dao.ExamerDao;
import edu.nefu.icec.exam.dao.QuestionDao;
import edu.nefu.icec.exam.dao.StudentDao;
import edu.nefu.icec.exam.dao.TeacherDao;
import edu.nefu.icec.exam.domain.Student;

public class ManagerTemplate {
	protected StudentDao studentDao;
	protected TeacherDao teacherDao;
	protected ExamDao examDao;
	protected QuestionDao questionDao;
	protected AnswerDao answerDao;
	protected ExamerDao examerDao;

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public ExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}

	
	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	
	public AnswerDao getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}

	public ExamerDao getExamerDao() {
		return examerDao;
	}

	public void setExamerDao(ExamerDao examerDao) {
		this.examerDao = examerDao;
	}

	/**
	 * 将Student转化为StudentBean
	 * 
	 * @param list
	 * @return
	 */
	public List<StudentBean> toBean(List<Student> list) {
		List<StudentBean> listBean = new ArrayList<StudentBean>();
		Iterator<Student> it = list.iterator();
		while (it.hasNext()) {
			StudentBean studentBean = new StudentBean();
			Student student = (Student) it.next();
			studentBean.setId(student.getSid());
			studentBean.setName(student.getName());
			studentBean.setNumber(student.getNumber());
			studentBean.setClassname(student.getClassname());
			listBean.add(studentBean);
		}
		return listBean;
	}

}
