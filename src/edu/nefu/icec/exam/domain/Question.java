package edu.nefu.icec.exam.domain;

import java.io.Serializable;

public class Question implements Serializable {

	/**
	 * 可序列化ID
	 */
	private static final long serialVersionUID = 1L;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 

	public Question(String qid, String title, String content) {
		super();
		this.qid = qid;
		this.title = title;
		this.content = content; 
	}

	public Question() {
		super();
	}

}
