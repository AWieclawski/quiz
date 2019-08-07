package edu.awieclawski.quiz.services;

import java.util.Arrays;

import org.springframework.stereotype.Component;

/*
 * Stripped down proxy of QuestionSet without any correct answer
 * 
*/
@Component
public class QuizSetProxy {

	private int questionNumber;
	private String question;
	private String[] arrayOfAnswers;

	public QuizSetProxy(int questionNumber, String question, String[] arrayOfAnswers) {
		this.questionNumber = questionNumber;
		this.question = question;
		this.arrayOfAnswers = arrayOfAnswers;
	}

	public QuizSetProxy() {

	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
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
		return "QuizSetProxy{" + "questionNumber=" + questionNumber + ", question='" + question + '\''
				+ ", arrayOfAnswers=" + Arrays.toString(arrayOfAnswers) + '}';
	}

}
