package edu.nefu.icec.exam.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Exam implements Serializable {

	/**
	 * 可序列的ID
	 */
	private static final long serialVersionUID = 1L;

	private String eid;
	private String name;
	private boolean enable;
	private Integer selected;
	private Date upload;
	private Teacher teacher; 

	private Set<Question> questions;
	private Set<Examer> examers;

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<Examer> getExamers() {
		return examers;
	}

	public void setExamers(Set<Examer> examers) {
		this.examers = examers;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public Date getUpload() {
		return upload;
	}

	public void setUpload(Date upload) {
		this.upload = upload;
	}
 

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Exam() {
		super();
	}

}
