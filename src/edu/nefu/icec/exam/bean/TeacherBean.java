package edu.nefu.icec.exam.bean;

import edu.nefu.icec.exam.domain.Teacher;

public class TeacherBean {
	private String id;
	private String number;
	private String name;
	private String password;
	private int examNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getExamNumber() {
		return examNumber;
	}

	public void setExamNumber(int examNumber) {
		this.examNumber = examNumber;
	}

	public TeacherBean() {
		super();
	}

	public TeacherBean(String id, String number, String name, String password,
			int examNumber) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.password = password;
		this.examNumber = examNumber;
	}

	public TeacherBean(Teacher teacher, int examNumber) {
		super();
		this.id = teacher.getTid();
		this.number = teacher.getNumber();
		this.name = teacher.getName();
		this.password = teacher.getPassword();
		this.examNumber = examNumber;
	}

}
