package edu.nefu.icec.exam.domain;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable {

	/**
	 * 可序列的ID
	 */
	private static final long serialVersionUID = 1L;

	private String tid;
	private String number;
	private String name;
	private String password;
	private List<Exam> exams;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public Teacher(String tid, String number, String name, String password,
			List<Exam> exams) {
		super();
		this.tid = tid;
		this.number = number;
		this.name = name;
		this.password = password;
		this.exams = exams;
	}

	public Teacher() {
		super();
	}

}
