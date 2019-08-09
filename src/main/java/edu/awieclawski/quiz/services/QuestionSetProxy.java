package edu.awieclawski.quiz.services;

import java.util.Arrays;

import org.springframework.stereotype.Component;

/*
 * Stripped down proxy of QuestionSet without correct answer, test type, difficulty level
 * 
*/
@Component
public class QuestionSetProxy {

	private Integer questionNumber;
	private String question;
	private String[] arrayOfAnswers;

	public QuestionSetProxy(int questionNumber, String question, String[] arrayOfAnswers) {
		this.questionNumber = questionNumber;
		this.question = question;
		this.arrayOfAnswers = arrayOfAnswers;
	}

	public QuestionSetProxy() {
	}
	
	public QuestionSetProxy(Integer questionNumber, String question, String[] arrayOfAnswers) {
		super();
		this.questionNumber = questionNumber;
		this.question = question;
		this.arrayOfAnswers = arrayOfAnswers;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getArrayOfAnswers() {
		return arrayOfAnswers;
	}

	public void setArrayOfAnswers(String[] arrayOfAnswers) {
		this.arrayOfAnswers = arrayOfAnswers;
	}

	@Override
	public String toString() {
		return "QeustionSetProxy{" + "questionNumber=" + questionNumber + ", question='" + question + '\''
				+ ", arrayOfAnswers=" + Arrays.toString(arrayOfAnswers) + '}';
	}

}
