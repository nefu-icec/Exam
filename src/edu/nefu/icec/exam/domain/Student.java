package edu.nefu.icec.exam.domain;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 可序列的ID
	 */
	private static final long serialVersionUID = 1L;

	private String sid;
	private String number;
	private String name;
	private String classname;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Student(String sid, String number, String name, String classname) {
		super();
		this.sid = sid;
		this.number = number;
		this.name = name;
		this.classname = classname;
	}

	public Student() {
		super();
	}

}
