package edu.nefu.icec.exam.domain;

import java.io.Serializable;

public class Answer implements Serializable {

	/**
	 * 可序列化ID
	 */
	private static final long serialVersionUID = 1L;

	private String aid;
	private String description;
	private Question question;
	private Double mark;
	private Integer answerstatement;

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getAnswerstatement() {
		return answerstatement;
	}

	public void setAnswerstatement(Integer answerstatement) {
		this.answerstatement = answerstatement;
	}

	public Answer(String aid, String description, Question question,
			Double mark, Integer answerstatement) {
		super();
		this.aid = aid;
		this.description = description;
		this.question = question;
		this.mark = mark;
		this.answerstatement = answerstatement;
	}

	public Answer() {
		super();
	}

}
