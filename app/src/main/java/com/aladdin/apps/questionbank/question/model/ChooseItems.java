package com.aladdin.apps.questionbank.question.model;

import java.io.Serializable;

public class ChooseItems implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5551534909847458629L;
	private String qiSeq;
	private String qiContent;
	private String qiComment;
	private Boolean ifCorrect; // �Ƿ��Ǵ�
	
	public ChooseItems(String qiSeq, String qiContent, String qiComment, Boolean ifCorrect) {
		super();
		this.qiSeq = qiSeq;
		this.qiContent = qiContent;
		this.qiComment = qiComment;
		this.ifCorrect = ifCorrect;
	}
	public String getQiSeq() {
		return qiSeq;
	}
	public void setQiSeq(String qiSeq) {
		this.qiSeq = qiSeq;
	}
	public String getQiContent() {
		return qiContent;
	}
	public void setQiContent(String qiContent) {
		this.qiContent = qiContent;
	}
	public String getQiComment() {
		return qiComment;
	}
	public void setQiComment(String qiComment) {
		this.qiComment = qiComment;
	}
	public Boolean getIfCorrect() {
		return ifCorrect;
	}
	public void setIfCorrect(Boolean ifCorrect) {
		this.ifCorrect = ifCorrect;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
