package com.aladdin.apps.questionbank.question.model;

import java.io.Serializable;
import java.util.List;

public class QuestionBank implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6407707422140749874L;
	
	private Long questionId;
	private String questionCategory; //Java
	private String questionType; //��ѡ,��ѡ,�ж�,�ʴ�
	private String questionSubject; //����
	private List<ChooseItems> chooseItemsList;
	private String answers;  //A,B
	private String questionComments;
	
	
	public QuestionBank(Long questionId, String questionCategory, String questionType, String questionSubject,
			List<ChooseItems> chooseItemsList, String answers, String questionComments) {
		super();
		this.questionId = questionId;
		this.questionCategory = questionCategory;
		this.questionType = questionType;
		this.questionSubject = questionSubject;
		this.chooseItemsList = chooseItemsList;
		this.answers = answers;
		this.questionComments = questionComments;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionCategory() {
		return questionCategory;
	}
	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionSubject() {
		return questionSubject;
	}
	public void setQuestionSubject(String questionSubject) {
		this.questionSubject = questionSubject;
	}
	public List<ChooseItems> getChooseItemsList() {
		return chooseItemsList;
	}
	public void setChooseItemsList(List<ChooseItems> chooseItemsList) {
		this.chooseItemsList = chooseItemsList;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public String getQuestionComments() {
		return questionComments;
	}
	public void setQuestionComments(String questionComments) {
		this.questionComments = questionComments;
	}
	
}
