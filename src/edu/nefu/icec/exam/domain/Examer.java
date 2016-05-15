package edu.nefu.icec.exam.domain;

import java.io.Serializable;
import java.util.Set;

public class Examer implements Serializable {

	/**
	 * 可序列的ID
	 */
	private static final long serialVersionUID = 1L;

	private String erid;
	private String password;
	private Double mark;
	private boolean haslogin;
	private Set<Answer> answers;
	private Student student;
	private Exam exam;

	public String getErid() {
		return erid;
	}

	public void setErid(String erid) {
		this.erid = erid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isHaslogin() {
		return haslogin;
	}

	public void setHaslogin(boolean haslogin) {
		this.haslogin = haslogin;
	}

	public Examer(String erid, String password, Double mark, boolean haslogin,
			Set<Answer> answers, Student student, Exam exam) {
		super();
		this.erid = erid;
		this.password = password;
		this.mark = mark;
		this.haslogin = haslogin;
		this.answers = answers;
		this.student = student;
		this.exam = exam;
	}

	public Examer() {
		super();
	}

}
