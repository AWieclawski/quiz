package edu.awieclawski.quiz.services;

import org.springframework.stereotype.Component;

@Component
public class TestRecapitulation {
	
	private Integer questionNumberInRecapitulation;
	private String selectedAnswerInRecapitulation;
	private String selectedAnswerContentInRecapitulation;
	private Boolean userSelectionsVerificationResult;
	
	public TestRecapitulation() {
		super();
	}
	public TestRecapitulation(Integer questionNumberInRecapitulation, String selectedAnswerInRecapitulation,
			String selectedAnswerContentInRecapitulation, Boolean userSelectionsVerificationResult) {
		super();
		this.questionNumberInRecapitulation = questionNumberInRecapitulation;
		this.selectedAnswerInRecapitulation = selectedAnswerInRecapitulation;
		this.selectedAnswerContentInRecapitulation = selectedAnswerContentInRecapitulation;
		this.userSelectionsVerificationResult = userSelectionsVerificationResult;
	}
	public Integer getQuestionNumberInRecapitulation() {
		return questionNumberInRecapitulation;
	}
	public void setQuestionNumberInRecapitulation(Integer questionNumberInRecapitulation) {
		this.questionNumberInRecapitulation = questionNumberInRecapitulation;
	}
	public String getSelectedAnswerInRecapitulation() {
		return selectedAnswerInRecapitulation;
	}
	public void setSelectedAnswerInRecapitulation(String selectedAnswerInRecapitulation) {
		this.selectedAnswerInRecapitulation = selectedAnswerInRecapitulation;
	}
	public String getSelectedAnswerContentInRecapitulation() {
		return selectedAnswerContentInRecapitulation;
	}
	public void setSelectedAnswerContentInRecapitulation(String selectedAnswerContentInRecapitulation) {
		this.selectedAnswerContentInRecapitulation = selectedAnswerContentInRecapitulation;
	}
	public Boolean getUserSelectionsVerificationResult() {
		return userSelectionsVerificationResult;
	}
	public void setUserSelectionsVerificationResult(Boolean userSelectionsVerificationResult) {
		this.userSelectionsVerificationResult = userSelectionsVerificationResult;
	}
	
	@Override
	public String toString() {
		return "TestRecapitulation [questionNumberInRecapitulation=" + questionNumberInRecapitulation
				+ ", selectedAnswerInRecapitulation=" + selectedAnswerInRecapitulation
				+ ", selectedAnswerContentInRecapitulation=" + selectedAnswerContentInRecapitulation
				+ ", userSelectionsVerificationResult=" + userSelectionsVerificationResult + "]";
	}
	
}
