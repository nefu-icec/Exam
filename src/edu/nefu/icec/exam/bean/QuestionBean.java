package edu.nefu.icec.exam.bean;

import edu.nefu.icec.exam.domain.Question;

public class QuestionBean {
	private String qid;
	private String title;
	private String content;

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public QuestionBean(String qid, String title, String content) {
		super();
		this.qid = qid;
		this.title = title;
		this.content = content;
	}

	public QuestionBean() {
		super();
	}

	public QuestionBean(Question question) {
		super();
		this.qid = question.getQid();
		this.title = question.getTitle();
		this.content = question.getContent();
	}

	
}
