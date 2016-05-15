package edu.nefu.icec.exam.bean;

import edu.nefu.icec.exam.domain.Student;

public class StudentBean {
	private String id;
	private String number;
	private String name;
	private String classname;

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

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public StudentBean(String id, String number, String name, String classname) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.classname = classname;
	}

	public StudentBean() {
		super();
	}

	public StudentBean(Student student) {
		super();
		this.id = student.getSid();
		this.number = student.getNumber();
		this.name = student.getName();
		this.classname = student.getClassname();
	}
}
