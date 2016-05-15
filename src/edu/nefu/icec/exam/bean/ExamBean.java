package edu.nefu.icec.exam.bean;

import java.util.Date;

import edu.nefu.icec.exam.domain.Exam;

public class ExamBean {
	private String id;
	private String name;
	private boolean enable;
	private int selected;
	private Date upload;
	private String tid; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public Date getUpload() {
		return upload;
	}

	public void setUpload(Date upload) {
		this.upload = upload;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
 

	public ExamBean(String id, String name, boolean enable, int selected,
			Date upload, String tid) {
		super();
		this.id = id;
		this.name = name;
		this.enable = enable;
		this.selected = selected;
		this.upload = upload;
		this.tid = tid; 
	}

	public ExamBean() {
		super();
	}

	public ExamBean(Exam exam) {
		super();
		this.id = exam.getEid();
		this.name = exam.getName();
		this.enable = exam.isEnable();
		this.selected = exam.getSelected();
		this.upload = exam.getUpload(); 
		this.tid = exam.getTeacher().getTid();
	}
}
