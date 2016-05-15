package edu.nefu.icec.exam.bean;

import edu.nefu.icec.exam.domain.Answer;

public class AnswerBean {
	private String id;
	private String description;
	private double mark;
	private String qid;
	private int answerstate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public int getAnswerstate() {
		return answerstate;
	}

	public void setAnswerstate(int answerstate) {
		this.answerstate = answerstate;
	}

	public AnswerBean(String id, String description, double mark) {
		super();
		this.id = id;
		this.description = description;
		this.mark = mark;
	}

	public AnswerBean() {
		super();
	}

	public AnswerBean(Answer answer) {
		super();
		this.id = answer.getAid();
		this.description = answer.getDescription();
		this.mark = answer.getMark();
		this.qid = answer.getQuestion().getQid();
		this.answerstate = answer.getAnswerstatement();
	}

}
