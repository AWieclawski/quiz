package edu.awieclawski.quiz.models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "qset_tb")
public class QuestionSet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long questionSetId;
	private String question;
	private String firstAnswer;
	private String secondAnswer;
	private String thirdAnswer;
	private String fourthAnswer;
	@JsonIgnore
	private Integer correctAnswer;
	@ManyToOne
	private Test test;

	public Long getQuestionSetId() {
		return questionSetId;
	}

	public void setQuestionSetId(Long questionSetId) {
		this.questionSetId = questionSetId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getFirstAnswer() {
		return firstAnswer;
	}

	public void setFirstAnswer(String firstAnswer) {
		this.firstAnswer = firstAnswer;
	}

	public String getSecondAnswer() {
		return secondAnswer;
	}

	public void setSecondAnswer(String secondAnswer) {
		this.secondAnswer = secondAnswer;
	}

	public String getThirdAnswer() {
		return thirdAnswer;
	}

	public void setThirdAnswer(String thirdAnswer) {
		this.thirdAnswer = thirdAnswer;
	}

	public String getFourthAnswer() {
		return fourthAnswer;
	}

	public void setFourthAnswer(String fourthAnswer) {
		this.fourthAnswer = fourthAnswer;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "QuestionSet [questionSetId=" + questionSetId + ", question=" + question + ", firstAnswer=" + firstAnswer
				+ ", secondAnswer=" + secondAnswer + ", thirdAnswer=" + thirdAnswer + ", fourthAnswer=" + fourthAnswer
				+ ", correctAnswer=" + "not allowed" + ", test=" + test + "]";
	}
	
	
}
