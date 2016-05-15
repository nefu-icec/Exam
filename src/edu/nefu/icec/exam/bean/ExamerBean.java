package edu.nefu.icec.exam.bean;

import edu.nefu.icec.exam.domain.Examer;

public class ExamerBean {
	private String erid;
	private String password;
	private Double mark;
	private String name;
	private String number;
	private String className;
	private String eid;
	private boolean stat;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public ExamerBean(Examer examer) {
		super();
		this.erid = examer.getErid();
		this.password = examer.getPassword();
		this.mark = examer.getMark();
		this.name = examer.getStudent().getName();
		this.number = examer.getStudent().getNumber();
		this.className = examer.getStudent().getClassname();
		this.eid = examer.getExam().getEid();
		this.stat = examer.isHaslogin();
	}

	public ExamerBean(String erid, String password, Double mark, String name,
			String number, String className, String eid) {
		super();
		this.erid = erid;
		this.password = password;
		this.mark = mark;
		this.name = name;
		this.number = number;
		this.className = className;
		this.eid = eid;
	}

	public ExamerBean() {
		super();
	}

}
