package edu.awieclawski.quiz.models;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "results_tb")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long resultId;
	private Integer goodAnswers;
	private LocalDate resultDate;
	@ManyToOne
	private User user;
	@ManyToOne
	private Test test;

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public Integer getGoodAnswers() {
		return goodAnswers;
	}

	public void setGoodAnswers(Integer goodAnswers) {
		this.goodAnswers = goodAnswers;
	}

	public LocalDate getResultDate() {
		return resultDate;
	}

	public void setResultDate(LocalDate resultDate) {
		this.resultDate = resultDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}
